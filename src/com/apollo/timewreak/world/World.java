package com.apollo.timewreak.world;

import com.apollo.timewreak.engine.CameraHandler;
import com.apollo.timewreak.engine.ShaderHandler;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class World {
    private byte[] tiles;
    private int WIDTH;
    private int HEIGHT;

    private Matrix4f world;

    public World(){
        WIDTH = 16;
        HEIGHT = 16;
        tiles = new byte[WIDTH * HEIGHT];
        world = new Matrix4f().setTranslation(new Vector3f(0));
        world.scale(64);
    }

    public void render(TileRenderer render, ShaderHandler shader, CameraHandler camera){
        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j < WIDTH; j++){
                render.renderTile(tiles[j + i * WIDTH], j, -i, shader, world,camera);
            }
        }
    }
}
