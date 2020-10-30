package com.apollo.timewreak.engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class KeyboardHandler {

    private static DisplayHandler displayHandler = new DisplayHandler();
    public float x = 0;
    public float y = 0;

    public void checkKeyboardInput(){
        if(glfwGetKey(displayHandler.getDisplay(), GLFW_KEY_D) == GL_TRUE){
            x+=1;
        }else if(glfwGetKey(displayHandler.getDisplay(), GLFW_KEY_A) == GL_TRUE){
            x-=1;
        }else if(glfwGetKey(displayHandler.getDisplay(), GLFW_KEY_W) == GL_TRUE){
            y+=1;
        }else if(glfwGetKey(displayHandler.getDisplay(), GLFW_KEY_S) == GL_TRUE){
            y-=1;
        }

        if(glfwGetKey(displayHandler.getDisplay(), GLFW_KEY_ESCAPE) == GL_TRUE){
            glfwSetWindowShouldClose(displayHandler.getDisplay(), true);
        }
    }

}
