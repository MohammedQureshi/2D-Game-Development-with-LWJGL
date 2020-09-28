package com.apollo.timewreak.main;

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

	private static void loop(){
		displayManager.createDisplay();

		while(!glfwWindowShouldClose(displayManager.WINDOW)){
			keyboardHandler.checkKeyboardInput();
			mouseHandler.checkMouseInput();

			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT); //Clears the display

			glBegin(GL_QUADS);
				glColor4f(0, 1, 1, 0); //Sets colour of vertex below
				glVertex2f(-0.75f+keyboardHandler.x,0.75f+keyboardHandler.y); //Sets Vertex
				glColor4f(1, 0, 1, 0);
				glVertex2f(0.75f+keyboardHandler.x,0.75f+keyboardHandler.y);
				glColor4f(1, 1, 0, 0);
				glVertex2f(0.75f+keyboardHandler.x,-0.75f+keyboardHandler.y);
				glColor4f(1, 1, 1, 0);
				glVertex2f(-0.75f+keyboardHandler.x,-0.75f+keyboardHandler.y);
			glEnd();


			glfwSwapBuffers(displayManager.WINDOW); //Swaps the buffer to you can draw to it with OpenGL
		}
		glfwTerminate();
	}

	public static void main(String[] args) {
		loop();
	}

}