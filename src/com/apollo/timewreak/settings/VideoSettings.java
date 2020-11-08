package com.apollo.timewreak.settings;

public class VideoSettings extends Setting {
    public VideoSettings() {
        this.setProperty("WIDTH", 1280);
        this.setProperty("HEIGHT", 720);
        this.setProperty("FULLSCREEN", false);
        this.setProperty("VSYNC", true);
        this.setProperty("FPS_CAP", 65);
        this.setSavable(true);
    }
}