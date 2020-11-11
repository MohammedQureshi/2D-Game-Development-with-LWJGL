package com.apollo.timewreak.world;

public class TileHandler {

    public static TileHandler tiles[] = new TileHandler[5];

    public static final TileHandler GrassTile = new TileHandler(0, "grass");
    public static final TileHandler StoneTile = new TileHandler(1, "rock");
    public static final TileHandler WoodTile = new TileHandler(2, "wood");
    public static final TileHandler SandTile = new TileHandler(3, "sand");
    public static final TileHandler PebblesTile = new TileHandler(4, "pebbles");

    private byte ID;
    private String texture;

    public TileHandler(final byte ID, String texture){
        this.ID = ID;
        this.texture = texture;
        if(tiles[ID] != null){
            throw new IllegalStateException("Tiles at: ["+ ID + "] is already being used!");
        }
        tiles[ID] = this;

    }

    public TileHandler(final int ID, String texture) {
        this((byte)ID, texture);
    }

    public byte getID() {
        return ID;
    }

    public void setID(final byte ID) {
        this.ID = ID;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }


}
