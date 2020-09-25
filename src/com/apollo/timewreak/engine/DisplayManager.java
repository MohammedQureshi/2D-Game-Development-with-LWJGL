package com.apollo.timewreak.engine;

import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

public class DisplayManager {

    private static int WIDTH = 1280;
    private static int HEIGHT = 720;
    public static long WINDOW;

    public void createDisplay(){
        if(!glfwInit()){ //Check to see if GLFW has been initialised
            throw new IllegalStateException("Failed to initialise GLFW!");
        }
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); //Sets window to visible and non resizable
        WINDOW = glfwCreateWindow(WIDTH, HEIGHT, "Project Apollo", 0,0); //Creates the display
        if(WINDOW == 0){ //Checks if a window can be created
            throw new IllegalStateException("Failed to crate window!");
        }
        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(WINDOW, (videoMode.width() - WIDTH) /2, (videoMode.height() - HEIGHT) /2);
        glfwShowWindow(WINDOW);

    }

}
