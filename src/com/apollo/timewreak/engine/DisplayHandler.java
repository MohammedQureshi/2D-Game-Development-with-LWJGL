package com.apollo.timewreak.engine;

import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;

public class DisplayHandler {

    public long Display;
    private int WIDTH, HEIGHT;

    public DisplayHandler(){
        setSize(1280, 720);
    }

    public void createDisplay(String TITLE){
        Display = glfwCreateWindow(WIDTH, HEIGHT, TITLE, 0, 0);
        if(Display == 0) throw new IllegalStateException(("Failed to create Display!"));

        //glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE); //Debug Mode
        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor()); //Gets monitor settings
        glfwSetWindowPos(Display, (videoMode.width() - WIDTH) /2, (videoMode.height() - HEIGHT) /2); //Centres display

        glfwShowWindow(Display);
        glfwMakeContextCurrent(Display);
    }

    public boolean shouldClose(){
        return glfwWindowShouldClose(Display) != false;
    }

    public void swapBuffers(){
        glfwSwapBuffers(Display); //Swaps the buffer to you can draw to it with OpenGL
    }

    public void setSize(int WIDTH, int HEIGHT){
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public int getWIDTH(){return WIDTH;}
    public int getHEIGHT(){return HEIGHT;}
}
