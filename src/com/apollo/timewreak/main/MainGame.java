package com.apollo.timewreak.main;

import com.apollo.timewreak.engine.DisplayManager;

import static org.lwjgl.glfw.GLFW.*;

public class MainGame {

	private static DisplayManager displayManager = new DisplayManager();

	public static void main(String[] args) {
		displayManager.createDisplay();

		while(!glfwWindowShouldClose(displayManager.WINDOW)){
			glfwPollEvents();
		}
		glfwTerminate();
	}

}