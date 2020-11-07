package com.apollo.timewreak.world;

public class TileHandler {

    public static TileHandler tiles[] = new TileHandler[4];

    public static final TileHandler sampleTile = new TileHandler((byte)0, "RoomBackground");
    public static final TileHandler newTile = new TileHandler((byte) 1, "test2");

    private byte ID;
    private String texture;

    public TileHandler(byte ID, String texture){
        this.ID = ID;
        this.texture = texture;
        if(tiles[ID] != null){
            throw new IllegalStateException("Tiles at: ["+ ID + "] is already being used!");
        }
        tiles[ID] = this;

    }

    public byte getID() {
        return ID;
    }

    public void setID(byte ID) {
        this.ID = ID;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }


}
