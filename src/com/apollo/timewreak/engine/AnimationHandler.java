package com.apollo.timewreak.engine;

import com.apollo.timewreak.inputOutput.TimerHandler;
import org.w3c.dom.Text;

public class AnimationHandler {
    private TextureHandler[] frames;
    private int pointer;

    private double elapsedTime;
    private double currentTime;
    private double lastTime;
    private double fps;
    private String fileName;
    public AnimationHandler(final int amount, final double fps, final String fileName) {
        this.fileName = fileName;
        this.pointer = 0;
        this.elapsedTime = 0;
        this.currentTime = 0;
        this.lastTime = TimerHandler.getTime();
        this.fps = 1.0/fps;


        // TODO: this method needs to be changed.
        //Loads all the different files that use the animation.
        this.frames = new TextureHandler[amount];
        for(int i = 0; i < amount; i++) {
            this.frames[i] = new TextureHandler("Animation/" + fileName + "_" + i + ".png");
        }
    }

    public String getFileName() { return this.fileName; }

    //Bind the texture
    public void bind() { bind(0); }

    //Run though the multiple textures and binds it each time a new texture comes onto screen
    public void bind(final int sampler) {
        this.currentTime = TimerHandler.getTime();
        this.elapsedTime += currentTime - lastTime;

        if(elapsedTime >= fps) {
            elapsedTime -= fps;
            pointer++;
        }

        if(pointer >= frames.length) pointer = 0;

        this.lastTime = currentTime;

        frames[pointer].bind(sampler);
    }
}