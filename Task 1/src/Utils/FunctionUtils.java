package Utils;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.sun.prism.impl.BufferUtil;

import java.nio.FloatBuffer;
import java.util.Vector;
import java.util.concurrent.Callable;

public class FunctionUtils {
    static void doWithBindedArrays(Vector<SVertexP3N> vertices, GLAutoDrawable drawable, Callable callable){

        GL2 gl = drawable.getGL().getGL2();

        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);

        FloatBuffer positions = fillPositionsArray(vertices);

        positions.rewind();

        gl.glVertexPointer(3, GL2.GL_FLOAT, 0, positions);

        try {
            callable.call();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL2.GL_NORMAL_ARRAY);

    }

    private static FloatBuffer fillPositionsArray(final Vector<SVertexP3N> vertices){

        FloatBuffer positions = BufferUtil.newFloatBuffer(vertices.size() * 3);

        for (SVertexP3N vertice : vertices) {

            positions.put(vertice.position.x);
            positions.put(vertice.position.y);
            positions.put(vertice.position.z);
        }

        return positions;
    }
}
