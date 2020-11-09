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

    public AnimationHandler(int amount, int fps, String filename) {
        this.pointer = 0;
        this.elapsedTime = 0;
        this.currentTime = 0;
        this.lastTime = TimerHandler.getTime();
        this.fps = 1.0/(double)fps;

        this.frames = new TextureHandler[amount];
        for(int i = 0; i < amount; i++) {
            this.frames[i] = new TextureHandler("Animation/" + filename + "_" + i + ".png");
        }
    }

    public void bind() { bind(0); }

    public void bind(int sampler) {
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