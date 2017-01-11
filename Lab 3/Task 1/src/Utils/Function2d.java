package Utils;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.sun.javafx.geom.Vec3f;

import java.util.Vector;

public class Function2d {
    private static final float SHIFT = 0.05f;

    private Vector<SVertexP3N> vertices;

    public Function2d(float length, int verticeAmount){

        vertices = new Vector<>();
        tesselate(length, verticeAmount);
    }

    private void tesselate(float length, int verticeAmount){

        for (int i = 0; i < verticeAmount; ++i)
        {
            SVertexP3N vertex = new SVertexP3N();
            vertex.position =  new Vec3f((- SHIFT * verticeAmount + i) * length / verticeAmount, -2.f, 0.f);
            vertices.add(vertex);
        }
    }

    private void drawElements(GL2 gl){

        gl.glDrawArrays(GL2.GL_POINTS, 0, vertices.size());
    }

    public void draw(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();

        FunctionUtils.doWithBindedArrays(vertices, drawable, () -> {
            drawElements(gl);
            return null;
        });
    }
}
