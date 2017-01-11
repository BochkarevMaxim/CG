package textures;

/**
 * Created by Qontrol on 10.01.2017.
 */
public class ModelTexture {

    private  int textureID;

    public int getTextureID() {
        return textureID;
    }

    public void setTextureID(int textureID) {
        this.textureID = textureID;
    }

    public ModelTexture(int id){
        this.textureID = id;
    }
}
