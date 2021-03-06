package models;

import textures.ModelTexture;

public class TextureModel {
    private RawModel rawModel;
    private ModelTexture texture;

    public RawModel getRawModel() {
        return rawModel;
    }

    public void setRawModel(RawModel rawModel) {
        this.rawModel = rawModel;
    }

    public ModelTexture getTexture() {
        return texture;
    }

    public void setTexture(ModelTexture texture) {
        this.texture = texture;
    }

    public TextureModel(RawModel model, ModelTexture texture){
        this.rawModel = model;
        this.texture = texture;
    }
}
