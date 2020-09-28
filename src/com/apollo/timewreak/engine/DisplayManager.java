package com.apollo.timewreak.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

public class DisplayManager {

    private static int WIDTH = 1280;
    private static int HEIGHT = 720;
    public static long WINDOW;

    public void createDisplay(){

        GLFWErrorCallback.createPrint(System.err).set(); // will print the error message in System.err.
        if(!glfwInit()){ //Check to see if GLFW has been initialised
            System.err.println("GLFW Failed to Initialise"); //Prints Error
            System.exit(1); // Exits System
        }
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will not be resizable

        WINDOW = glfwCreateWindow(WIDTH, HEIGHT, "Project Apollo", 0,0); //Creates the display

        if(WINDOW == 0) throw new IllegalStateException("Failed to crate window!"); //Checks to see if window was created

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor()); //Gets monitor settings
        glfwSetWindowPos(WINDOW, (videoMode.width() - WIDTH) /2, (videoMode.height() - HEIGHT) /2); //Centres display
        glfwShowWindow(WINDOW);

        glfwMakeContextCurrent(WINDOW); //Makes GLFW Context Current
        GL.createCapabilities(); //Creates Context

        GL.createCapabilities();

    }

}
