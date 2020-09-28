package com.apollo.timewreak.engine;

import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class MouseHandler {

    private static DisplayManager displayManager = new DisplayManager();

    private DoubleBuffer xMouse = BufferUtils.createDoubleBuffer(1);
    private DoubleBuffer yMouse = BufferUtils.createDoubleBuffer(1);
    public void checkMouseInput(){
        glfwGetCursorPos(displayManager.WINDOW, xMouse, yMouse);
        if(glfwGetMouseButton(displayManager.WINDOW, GLFW_MOUSE_BUTTON_2) == GL_TRUE){
            System.out.println("x : " + b1.get(0) + ", y = " + b2.get(0));
        }
    }

    public void checkMouseOver(){

    }

}
