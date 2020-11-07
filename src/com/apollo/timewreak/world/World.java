package com.apollo.timewreak.world;

import com.apollo.timewreak.engine.CameraHandler;
import com.apollo.timewreak.engine.ShaderHandler;
import com.apollo.timewreak.inputOutput.DisplayHandler;
import com.apollo.timewreak.main.Config;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import javax.swing.*;

public class World {
    private final int view = 6;
    private byte[] tiles;
    private int WIDTH;
    private int HEIGHT;

    private Matrix4f world;

    public World(){
        WIDTH = 2;
        HEIGHT = 2;
        tiles = new byte[WIDTH * HEIGHT];
        world = new Matrix4f().setTranslation(new Vector3f(0));
        world.scale(Config.GAME_SCALE);
    }

    public void render(TileRenderer render, ShaderHandler shader, CameraHandler camera, DisplayHandler display){
//        for(int i = 0; i < HEIGHT; i++){
//            for(int j = 0; j < WIDTH; j++){
//                render.renderTile(tiles[j + i * WIDTH], j, -i, shader, world,camera);
//            }
//        }
        int posX = ((int) camera.getPosition().x + (display.getWIDTH()/2)) / (Config.GAME_SCALE * 2);
        int posY = ((int) camera.getPosition().y - (display.getHEIGHT()/2)) / (Config.GAME_SCALE * 2);

        for(int i = 0; i < view; i++){
            for(int j = 0; j < view; j++){
                TileHandler tile = getTile(i-posX, j-posY);
                if(tile != null){
                    render.renderTile(tile, i-posX, -j-posY, shader, world, camera);
                }
            }
        }
    }

    public void correctCamera(CameraHandler camera, DisplayHandler display){
        Vector3f position = camera.getPosition();

        int width = -WIDTH * Config.GAME_SCALE * 2;
        int height = HEIGHT * Config.GAME_SCALE * 2;

        if(position.x > -(display.getWIDTH()/2)+ Config.GAME_SCALE) position.x = -(display.getWIDTH()/2) + Config.GAME_SCALE;
        if(position.x < width + (display.getWIDTH()/2) + Config.GAME_SCALE) position.x = width + (display.getWIDTH()/2) + Config.GAME_SCALE;

        if(position.y < (display.getHEIGHT()/2) - Config.GAME_SCALE) position.y = (display.getHEIGHT()/2) - Config.GAME_SCALE;
        if(position.y > height -(display.getHEIGHT() / 2) -Config.GAME_SCALE) position.y = height-(display.getHEIGHT()/2) - Config.GAME_SCALE;
    }

    public void setTile(TileHandler tile, int x, int y){
        tiles[x + y * WIDTH] = tile.getID();
    }

    public TileHandler getTile(int x, int y){
        try{
            return TileHandler.tiles[tiles[x + y * WIDTH]];
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
}
