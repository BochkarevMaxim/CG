package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
public class displayManager {
    private static final int WIDTH = 1368;
    private static final int HEIGHT = 768;
    private static final int FPS_CAP = 60;


    public static void createDisplay(){

        ContextAttribs attributes = new ContextAttribs(3,2);
        attributes.withForwardCompatible(true);
        attributes.withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(new PixelFormat(), attributes);
            Display.setTitle("Computer Graphics. TASK #4");
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(0 ,0, WIDTH, HEIGHT);
    }

    public static void updateDisplay(){
        Display.sync(FPS_CAP);
        Display.update();
    }

    public static void closeDisplay(){
        Display.destroy();
    }
}
