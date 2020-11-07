package com.apollo.timewreak.main;

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;

public class Config {
    public static final String GAME_NAME = "Project Apollo";
    public static int GAME_WIDTH = 1280;
    public static int GAME_HEIGHT = 720;
    public static boolean DISPLAY_FULLSCREEN = false;
    public static long PRIMARY_DISPLAY = glfwGetPrimaryMonitor();
    public static double MAX_FPS = 65;
    public static int CAMERA_SPEED = 5;
    public static final int GAME_SCALE = 32;
    public class Server {
        public static final int HOST_PORT = 5432;
    }

}
