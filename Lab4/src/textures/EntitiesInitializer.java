package textures;

import entities.Entity;
import models.RawModel;
import models.TextureModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import renderEngine.OBJLoader;

import java.util.List;
import java.util.Random;

public class EntitiesInitializer {

    static final private int FIELD_SIZE = 400;
    static final private int PINE_APPLE_NUM = 90;
    static final private int LEAFE_TREE_NUM = 20;
    static final private int SMALL_PINE_TREE_NUM = 40;
    static final private int FERN_NUM = 50;
    static final private int GRASS_BUSHES_NUM = 1000;
    static final private Random random = new Random();

    static private Vector3f getModelCoordinates(Terrain terrain){
        float x = random.nextFloat() * FIELD_SIZE;
        float z = random.nextFloat() * FIELD_SIZE;
        float y = terrain.getHeightOfTerrain(x,z);
        return new Vector3f(x, y, z);
    }

    static public void loadTextures(Loader loader, List<Entity> entities, Terrain terrain){

        RawModel pineTreeModel = OBJLoader.loadObjModel("tree", loader);
        RawModel fernModel = OBJLoader.loadObjModel("fern", loader);
        RawModel grassModel = OBJLoader.loadObjModel("grassModel", loader);
        RawModel usualTree = OBJLoader.loadObjModel("rocks", loader);
        RawModel pine2Model = OBJLoader.loadObjModel("pine", loader);

        TextureModel fern = new TextureModel(fernModel,new ModelTexture(loader.loadTexture("fern")));
        TextureModel grass = new TextureModel(grassModel,new ModelTexture(loader.loadTexture("grassTexture")));
        TextureModel tree = new TextureModel(usualTree,new ModelTexture(loader.loadTexture("rocks")));
        TextureModel pineTree2 = new TextureModel(pine2Model,new ModelTexture(loader.loadTexture("pine")));

        for(int i = 0; i < PINE_APPLE_NUM; i++){

            entities.add(new Entity(pineTree2, getModelCoordinates(terrain),
                    0,0,0,random.nextFloat() * 10));
        }

        for(int i=0; i < LEAFE_TREE_NUM; i++){
            entities.add(new Entity(tree, getModelCoordinates(terrain),
                    random.nextFloat()*10,0,0,random.nextFloat() * 1 + 1));
        }
        for(int i = 0; i < SMALL_PINE_TREE_NUM; i++){
            entities.add(new Entity(pineTree2, getModelCoordinates(terrain),
                    random.nextFloat()*10,0,0,random.nextFloat() * 6));
        }
        for(int i = 0; i < FERN_NUM; i++){
            entities.add(new Entity(fern, getModelCoordinates(terrain),
                    0,0,0,random.nextFloat() * 3));
        }
        for(int i = 0; i < GRASS_BUSHES_NUM; i++){
            entities.add(new Entity(grass, getModelCoordinates(terrain),
                    0,0,0,random.nextFloat() * 3));
        }
    }
}
