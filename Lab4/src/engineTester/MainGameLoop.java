package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.displayManager;
import textures.ModelTexture;
import textures.Terrain;

import java.util.ArrayList;
import java.util.List;

import static textures.EntitiesInitializer.loadTextures;

public class MainGameLoop {

    public static void main(String[] args) {
        displayManager.createDisplay();
        Loader loader = new Loader();

        List<Entity> entities = new ArrayList<Entity>();

        Light light = new Light(new Vector3f(100, 2400, 100), new Vector3f(1, 1, 1));
        Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grass")),
                "heightmap");
        loadTextures(loader, entities, terrain);

        Camera camera = new Camera();
        MasterRenderer renderer = new MasterRenderer(loader);

        while (!Display.isCloseRequested()) {
            camera.move();

            renderer.processTerrain(terrain);
            for (Entity ent : entities) {
                renderer.processEntity(ent);
            }

            renderer.render(light, camera);
            displayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();

        displayManager.closeDisplay();
    }
}