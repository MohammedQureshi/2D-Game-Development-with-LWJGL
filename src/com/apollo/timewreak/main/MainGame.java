package com.apollo.timewreak.main;

import com.apollo.timewreak.engine.DisplayManager;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class MainGame {

	private static DisplayManager displayManager = new DisplayManager();

	private static void loop(){
		displayManager.createDisplay();

		while(!glfwWindowShouldClose(displayManager.WINDOW)){
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT); //Clears the display

			glBegin(GL_QUADS);
				glColor4f(0, 1, 1, 0); //Sets colour of vertex below
				glVertex2f(-1.0f,1.0f); //Sets Vertex
				glColor4f(1, 0, 1, 0);
				glVertex2f(1f,1f);
				glColor4f(1, 1, 0, 0);
				glVertex2f(1f,-1f);
				glColor4f(1, 1, 1, 0);
				glVertex2f(-1f,-1f);
			glEnd();

			glfwSwapBuffers(displayManager.WINDOW); //Swaps the buffer to you can draw to it with OpenGL
		}
		glfwTerminate();
	}

	public static void main(String[] args) {
		loop();
	}

}