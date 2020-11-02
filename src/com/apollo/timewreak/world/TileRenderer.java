package com.apollo.timewreak.world;

import com.apollo.timewreak.engine.CameraHandler;
import com.apollo.timewreak.engine.ModelHandler;
import com.apollo.timewreak.engine.ShaderHandler;
import com.apollo.timewreak.engine.TextureHandler;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.HashMap;

public class TileRenderer {
    private HashMap<String, TextureHandler> tileTextures;
    private ModelHandler model;

    public TileRenderer(){
        tileTextures = new HashMap<String, TextureHandler>();
        float[] vertices = new float[]{
                -1f, 1f, 0, //Top Left 0
                1f, 1f, 0, //Top Right 1
                1f, -1f, 0, //Bottom Right 2
                -1f, -1f, 0, //Bottle Left 3
        };

        float[] texture = new float[]{
                0,0,
                1,0,
                1,1,
                0,1,
        };

        int[] indices = new int[]{
                0, 1, 2,
                2, 3, 0,
        };

        model = new ModelHandler(vertices, texture, indices);
        for(int i = 0; i < TileHandler.tiles.length; i++){
            if(TileHandler.tiles[i] != null){
                if(!tileTextures.containsKey(TileHandler.tiles[i].getTexture())){
                    String textureUsed = TileHandler.tiles[i].getTexture();
                    tileTextures.put(textureUsed, new TextureHandler((textureUsed + ".png")));
                }
            }
        }
    }

    public void renderTile(byte ID, int x, int y, ShaderHandler shader, Matrix4f world, CameraHandler camera){
        shader.bind();
        if(tileTextures.containsKey(TileHandler.tiles[ID].getTexture()))
            tileTextures.get(TileHandler.tiles[ID].getTexture()).bind(0);

        Matrix4f tilePosition = new Matrix4f().translate(new Vector3f(x*2, y*2, 0));
        Matrix4f target = new Matrix4f();

        camera.getProjection().mul(world, target);
        target.mul(tilePosition);
        shader.setUniform("sampler", 0);
        shader.setUniform("projection" , target);
        model.render();
    }
}
