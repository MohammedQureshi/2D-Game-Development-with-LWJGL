package com.apollo.timewreak.inputOutput;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;

public class DisplayHandler {

    private long Display;
    private int WIDTH, HEIGHT;
    private boolean FULLSCREEN;

    private InputHandler input;

    public static void setCallbacks(){
        glfwSetErrorCallback(new GLFWErrorCallback() {
            @Override
            public void invoke(int error, long description) {
                throw new IllegalStateException(GLFWErrorCallback.getDescription(description));
            }
        });
    }

    public DisplayHandler(){
        setSize(1280, 720);
        setFullscreen(false);
    }

    public void createDisplay(String TITLE) {

        Display = glfwCreateWindow(WIDTH, HEIGHT, TITLE, FULLSCREEN ? glfwGetPrimaryMonitor() : 0, 0);

        if (Display == 0) throw new IllegalStateException(("Failed to create Display!"));

        if(!FULLSCREEN){
            GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor()); //Gets monitor settings
            glfwSetWindowPos(Display, (videoMode.width() - WIDTH) / 2, (videoMode.height() - HEIGHT) / 2); //Centres display
            glfwShowWindow(Display);
        }
        glfwMakeContextCurrent(Display);
        input = new InputHandler(Display);
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

    public void update(){
        input.update();
        glfwPollEvents();
    }

    public void setFullscreen(boolean fullscreen){this.FULLSCREEN = fullscreen; }
    public boolean isFullscreen(){ return FULLSCREEN; }
    public long getWindow(){return Display;};
    public InputHandler getInput(){return input;}
    public int getWIDTH(){return WIDTH;}
    public int getHEIGHT(){return HEIGHT;}
}
