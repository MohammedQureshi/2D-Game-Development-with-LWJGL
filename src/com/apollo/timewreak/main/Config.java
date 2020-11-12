package com.apollo.timewreak.main;

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;


public class Config {
    public static final String GAME_NAME = "Project Apollo";
    public static int GAME_WIDTH = 1280;
    public static int GAME_HEIGHT = 720;
    public static double MAX_FPS = 65;
    public static int PLAYER_SPEED = 5;
    public static final int GAME_SCALE = 16;
    public static DevelopmentEnvironment DEV_ENVIRONMENT = DevelopmentEnvironment.DEVELOPMENT;
    public static String BUILD_VERSION = "0.1";
}
