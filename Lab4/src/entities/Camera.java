package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
    private Vector3f position = new Vector3f(75,25,75);
    private float pitch = 0;
    private float yaw = 130;
    private float roll;

    public Camera(){}

    public void move(){
        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            if (pitch > -20)
                pitch-=1f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            if (pitch < 20)
                pitch+=1f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            yaw-=1f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            yaw+=1f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_E)){
            if(((position.y >= 25)))
                position.y-=0.5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
            if((position.y <= 85))
            position.y+=0.5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            if((position.z > 0) )
            position.z-=0.5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            if((position.x <= 400))
            position.x+=0.5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            if((position.x >= 0))
            position.x-=0.5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            if((position.z <= 400))
            position.z+=0.5f;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
