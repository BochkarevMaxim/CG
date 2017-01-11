package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
    private final float CAMERA_ROTATE_STEP = 1.0f;
    private final float CAMERA_MOVEMENT_STEP = 0.5f;
    private final int SCENE_FIELD_SIZE = 400;
    private final int CAMERA_MAX_Y_COORD = 85;
    private final int CAMERA_MIN_Y_COORD = 25;
    private final int CAMERA_MAX_ROTATION_ANGLE = 25;

    private Vector3f position = new Vector3f(75,25,75);
    private float pitch = 0;
    private float yaw = 130;
    private float roll;

    public Camera(){}

    public void move(){
        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            if (pitch > -CAMERA_MAX_ROTATION_ANGLE)
                pitch -= CAMERA_ROTATE_STEP;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            if (pitch < CAMERA_MAX_ROTATION_ANGLE)
                pitch += CAMERA_ROTATE_STEP;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            yaw -= CAMERA_ROTATE_STEP;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            yaw += CAMERA_ROTATE_STEP;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_E)){
            if(((position.y >= CAMERA_MIN_Y_COORD)))
                position.y -= CAMERA_MOVEMENT_STEP;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
            if((position.y <= CAMERA_MAX_Y_COORD))
            position.y += CAMERA_MOVEMENT_STEP;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            if((position.z > 0) )
            position.z -= CAMERA_MOVEMENT_STEP;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            if(position.x <= SCENE_FIELD_SIZE)
            position.x += CAMERA_MOVEMENT_STEP;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            if((position.x >= 0))
            position.x -= CAMERA_MOVEMENT_STEP;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            if((position.z <= SCENE_FIELD_SIZE))
            position.z += CAMERA_MOVEMENT_STEP;
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
