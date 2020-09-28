package com.apollo.timewreak.engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class KeyboardHandler {

    private static DisplayManager displayManager = new DisplayManager();
    public float x = 0;
    public float y = 0;

    public void checkKeyboardInput(){
        if(glfwGetKey(displayManager.WINDOW, GLFW_KEY_D) == GL_TRUE){
            x+=0.001f;
        }else if(glfwGetKey(displayManager.WINDOW, GLFW_KEY_A) == GL_TRUE){
            x-=0.001f;
        }else if(glfwGetKey(displayManager.WINDOW, GLFW_KEY_W) == GL_TRUE){
            y+=0.001f;
        }else if(glfwGetKey(displayManager.WINDOW, GLFW_KEY_S) == GL_TRUE){
            y-=0.001f;
        }

        if(glfwGetKey(displayManager.WINDOW, GLFW_KEY_ESCAPE) == GL_TRUE){
            glfwSetWindowShouldClose(displayManager.WINDOW, true);
        }
    }

}
