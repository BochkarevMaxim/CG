package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TextureModel;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.displayManager;
import textures.ModelTexture;
import textures.Terrain;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGameLoop {

    public static void main(String[] args)
    {
        displayManager.createDisplay();
        Loader loader = new Loader();

        List<Entity> entities = new ArrayList<Entity>();

        Light light = new Light(new Vector3f(100,2400, 100), new Vector3f(1,1,1));
        Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")),
                "heightmap");
        loadTextures(loader, entities, terrain);

        Camera camera = new Camera();
        MasterRenderer renderer = new MasterRenderer(loader);

        while(!Display.isCloseRequested()){
            camera.move();

            renderer.processTerrain(terrain);
            for(Entity ent:entities){
                renderer.processEntity(ent);
            }

            renderer.render(light, camera);
            displayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();

        displayManager.closeDisplay();
    }

    static private void loadTextures(Loader loader, List<Entity> entities, Terrain terrain){
        RawModel pineTreeModel = OBJLoader.loadObjModel("tree", loader);
        RawModel fernModel = OBJLoader.loadObjModel("fern", loader);
        RawModel grassModel = OBJLoader.loadObjModel("grassModel", loader);
        RawModel usualTree = OBJLoader.loadObjModel("rocks", loader);
        RawModel pine2Model = OBJLoader.loadObjModel("pine", loader);

        TextureModel pineTree = new TextureModel(pineTreeModel,new ModelTexture(loader.loadTexture("tree")));
        TextureModel fern = new TextureModel(fernModel,new ModelTexture(loader.loadTexture("fern")));
        TextureModel grass = new TextureModel(grassModel,new ModelTexture(loader.loadTexture("grassTexture")));
        TextureModel tree = new TextureModel(usualTree,new ModelTexture(loader.loadTexture("rocks")));
        TextureModel pineTree2 = new TextureModel(pine2Model,new ModelTexture(loader.loadTexture("pine")));

        Random random = new Random();

        for(int i=0;i<90;i++){
            float x = random.nextFloat() * 400;
            float z = random.nextFloat() * 400;
            float y = terrain.getHeightOfTerrain(x,z);
            entities.add(new Entity(pineTree2, new Vector3f(x,y, z),
                    0,0,0,random.nextFloat() * 10));
        }
        // TODO: constants
        for(int i=0;i<20;i++){
            float x = random.nextFloat() * 400 ;
            float z = random.nextFloat() * 400;
            float y = terrain.getHeightOfTerrain(x,z);
            entities.add(new Entity(tree, new Vector3f(x,y, z),random.nextFloat()*10
                    ,0,0,random.nextFloat() * 1 + 1));
        }
        for(int i=0;i<40;i++){
            float x = random.nextFloat() * 400 ;
            float z = random.nextFloat() * 400;
            float y = terrain.getHeightOfTerrain(x,z);
            entities.add(new Entity(pineTree2, new Vector3f(x,y, z),random.nextFloat()*10
                    ,0,0,random.nextFloat() * 6));
        }
        for(int i=0;i<50;i++){
            float x = random.nextFloat() * 400 ;
            float z = random.nextFloat() * 400;
            float y = terrain.getHeightOfTerrain(x,z);
            entities.add(new Entity(fern, new Vector3f(x,y, z),0,0,0,random.nextFloat() * 3));
        }
        for(int i=0;i<90;i++){
            float x = random.nextFloat()* 400;
            float z = random.nextFloat()* 400;
            float y = terrain.getHeightOfTerrain(x,z);
            entities.add(new Entity(grass, new Vector3f(x,y, z),0,0,0,random.nextFloat() * 3));
        }
    }
}

