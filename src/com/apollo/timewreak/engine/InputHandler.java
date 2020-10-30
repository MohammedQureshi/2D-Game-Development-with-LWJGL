package com.apollo.timewreak.engine;

import static org.lwjgl.glfw.GLFW.*;

public class InputHandler {
    private long display;

    private boolean keys[];

    public InputHandler(long display){
        this.display = display;
        this.keys = new boolean[GLFW_KEY_LAST];
        for(int i = 0; i < GLFW_KEY_LAST; i++){
            keys[i] = false;
        }
    }
    public boolean isKeyDown(int key) {
        return glfwGetKey(display, key) == 1;
    }
    public boolean isKeyPressed(int key){
        return (isKeyDown(key) && !keys[key]);
    }
    public boolean isKeyReleased(int key){
        return (!isKeyDown(key) && keys[key]);
    }
    public boolean isMouseButtonDown(int button){
        return glfwGetMouseButton(display, button) == 1;
    }
    public void update(){
        for(int i = 32; i < GLFW_KEY_LAST; i++){
            keys[i] = isKeyDown(i);
        }
    }
}
