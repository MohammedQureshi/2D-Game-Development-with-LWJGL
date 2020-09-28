package com.apollo.timewreak.engine;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.*;

public class BasicQuadRender {
    private static DisplayManager displayManager = new DisplayManager();
    private static KeyboardHandler keyboardHandler = new KeyboardHandler();
    private static MouseHandler mouseHandler = new MouseHandler();
    public void renderBasicQuad(){
        keyboardHandler.checkKeyboardInput(); //Check keyboard input

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

}
