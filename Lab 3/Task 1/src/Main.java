import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    private static String TITLE = "COMPUTER GRAPHICS LAB3 TASK1";   // window's title
    private static final int CANVAS_WIDTH = 500;                 // width of the drawable
    private static final int CANVAS_HEIGHT = 500;                // height of the drawable
    private static final int FPS = 30;                            // animator's target frames per second

    public static void main(String argc[]) {

        // Using material from :
        // http://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
        SwingUtilities.invokeLater(() -> {
            CanabolaDrawer canabolaDrawer = new CanabolaDrawer();
            canabolaDrawer.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

            // Create a animator that drives canabolaDrawer' display() at the specified FPS.
            final FPSAnimator animator = new FPSAnimator((GLAutoDrawable) canabolaDrawer, FPS, true);

            // Create the top-level container
            final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
            frame.getContentPane().add(canabolaDrawer);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    new Thread(() -> {
                        if (animator.isStarted()) {
                            animator.stop();
                        }
                        System.exit(0);
                    }).start();
                }
            });
            frame.setTitle(TITLE);
            frame.pack();
            frame.setVisible(true);
            animator.start();
        });
    }
}
