package com.apollo.timewreak.main;

import com.apollo.timewreak.engine.BasicQuadRender;
import com.apollo.timewreak.engine.DisplayManager;
import com.apollo.timewreak.engine.KeyboardHandler;
import com.apollo.timewreak.engine.MouseHandler;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class MainGame {

	private static DisplayManager displayManager = new DisplayManager();
	private static KeyboardHandler keyboardHandler = new KeyboardHandler();
	private static MouseHandler mouseHandler = new MouseHandler();
	private static BasicQuadRender basicQuadRender = new BasicQuadRender();

	private static void loop(){
		displayManager.createDisplay();

		while(!glfwWindowShouldClose(displayManager.WINDOW)){ //While window is not closed

			keyboardHandler.checkKeyboardInput(); //Check keyboard input //Per Class needs to be ran
			mouseHandler.checkMouseInput(); //Check mouse input
			basicQuadRender.renderBasicQuad(); //Render basic quad

		}
		glfwTerminate();
	}

	public static void main(String[] args) {
		loop();
	}

}