package com.apollo.timewreak.inputOutput;

import com.apollo.timewreak.main.Config;
import com.apollo.timewreak.settings.VideoSettings;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;

public class DisplayHandler {

    private long Display;
    private boolean FULLSCREEN;
    private VideoSettings videoSettings;
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
        videoSettings = new VideoSettings();
        videoSettings.setPropertyIfFound("FULLSCREEN", true);
        setFullscreen(Boolean.valueOf(videoSettings.getProperty("FULLSCREEN")));
    }

    public void createDisplay(String TITLE) {

        Display = glfwCreateWindow(Config.GAME_WIDTH, Config.GAME_HEIGHT, TITLE, FULLSCREEN ? glfwGetPrimaryMonitor() : 0, 0);

        if (Display == 0) throw new IllegalStateException(("Failed to create Display!"));

        if(!FULLSCREEN){
            GLFWVidMode videoMode = glfwGetVideoMode(Screen.getPrimaryDisplay()); //Gets monitor settings
            glfwSetWindowPos(Display, (videoMode.width() - Config.GAME_WIDTH) / 2, (videoMode.height() - Config.GAME_HEIGHT) / 2); //Centres display
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

    public void update(){
        input.update();
        glfwPollEvents();
    }

    public void setFullscreen(boolean fullscreen){this.FULLSCREEN = fullscreen; }
    public boolean isFullscreen(){ return FULLSCREEN; }
    public long getWindow(){return Display;};
    public InputHandler getInput(){return input;}
    public int getWIDTH(){return Config.GAME_WIDTH;}
    public int getHEIGHT(){return Config.GAME_HEIGHT;}
}
