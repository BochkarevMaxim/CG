import Shaders.ShaderCompileException;
import Shaders.ShaderProgram;
import Shaders.ShaderType;
import Utils.Function2d;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import java.io.File;
import java.util.Scanner;

@SuppressWarnings("serial")
public class CanabolaDrawer extends GLCanvas implements GLEventListener {

    private static final float LENGTH = 1.f;
    private static final int VERTICIES = (int)LENGTH * 500 * (int)Math.PI;

    private GLU glu;
    private ShaderProgram shaderProgram;
    private TwistValueController twistController;
    private Function2d function;

    CanabolaDrawer() {
        this.addGLEventListener(this);
        this.setFocusable(true);
    }

    @Override
    public void init(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();

		twistController = new TwistValueController();
		this.addKeyListener(twistController);
		function = new Function2d(LENGTH, VERTICIES);
        initShaderProgram(gl);
        initGLContext(gl);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        GL2 gl = drawable.getGL().getGL2();

        if (height == 0){
            height = 1;
        }

        final float fieldOfView = 60.f;
        final float aspect = (float)width / height;
        final float zNear = 0.1f;
        final float zFar = 100.f;

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(fieldOfView, aspect, zNear, zFar);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -10.0f);
        shaderProgram.start(gl);
        try {
            int twist = shaderProgram.findUniform(gl, "TWIST");
            gl.glUniform1f(twist, twistController.getCurrentTwistValue());
        }
        catch (ShaderCompileException e){
            System.out.println("Could not compile shader!");
        }
        catch (Exception e){
            System.out.println("There is another problem");
        }

        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        function.draw(drawable);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);

        twistController.update();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

        shaderProgram.stop(drawable.getGL().getGL2());
    }

    private void initGLContext(GL2 gl){

        gl.glClearColor(1f, 1f, 1f, 1f);
        gl.glClearDepth(1f);
        gl.glEnable(GL2.GL_DEPTH_TEST);

        gl.glEnable(GL2.GL_CCW);
        gl.glEnable(GL2.GL_BACK);
        gl.glEnable(GL2.GL_CULL_FACE);
    }

    private void initShaderProgram(GL2 gl){

        String source = null;
        try {
             source = new Scanner(new File("src/Shaders/canabolaVertexShader.vert")).useDelimiter("\\Z").next();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        shaderProgram = new ShaderProgram(gl);
        shaderProgram.compileShader(gl, source, ShaderType.Vertex);
        shaderProgram.link(gl);
    }


}
