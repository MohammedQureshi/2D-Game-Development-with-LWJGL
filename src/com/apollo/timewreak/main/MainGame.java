package com.apollo.timewreak.main;

import com.apollo.timewreak.engine.*;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;

public class MainGame {

	private static KeyboardHandler keyboardHandler = new KeyboardHandler();
	private static MouseHandler mouseHandler = new MouseHandler();

    private static int WIDTH = 1280;
    private static int HEIGHT = 720;
    public static long WINDOW;

	private static void mainGameLoop(){
        GLFWErrorCallback.createPrint(System.err).set(); // will print the error message in System.err.
        if(!glfwInit()){ //Check to see if GLFW has been initialised
            System.err.println("GLFW Failed to Initialise"); //Prints Error
            System.exit(1); // Exits System
        }
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will not be resizable
        //glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE); //Debug Mode

        WINDOW = glfwCreateWindow(WIDTH, HEIGHT, "Project Apollo", 0,0); //Creates the display

        if(WINDOW == 0) throw new IllegalStateException("Failed to crate window!"); //Checks to see if window was created

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor()); //Gets monitor settings
        glfwSetWindowPos(WINDOW, (videoMode.width() - WIDTH) /2, (videoMode.height() - HEIGHT) /2); //Centres display
        glfwShowWindow(WINDOW);

        glfwMakeContextCurrent(WINDOW); //Makes GLFW Context Current
        GL.createCapabilities(); //Creates Context

        GL.createCapabilities();
        //GLUtil.setupDebugMessageCallback(); //Debug Checker

        glEnable(GL_TEXTURE_2D); //Enable 2D Textures
        glEnable(GL_BLEND); //Enable Blending
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //Allow Alpha channel

        float[] vertices = new float[]{
                -0.5f, 0.5f, 0, //Top Left 0
                0.5f, 0.5f, 0, //Top Right 1
                0.5f, -0.5f, 0, //Bottom Right 2
                -0.5f, -0.5f, 0, //Bottle Left 3
        };

        float[] texture = new float[]{
                0,0,
                1,0,
                1,1,
                0,1,
        };

        int[] indices = new int[]{
                0, 1, 2,
                2, 3, 0,
        };

        ModelHandler model = new ModelHandler(vertices, texture, indices);
        ShaderHandler shaderHandler = new ShaderHandler("VertexShader", "FragmentShader");
        TextureHandler sampleTexture = new TextureHandler("test.png"); //Load Texture
        Matrix4f projection = new Matrix4f().ortho2D(-WIDTH/2, WIDTH/2, -HEIGHT/2, HEIGHT/2);
        Matrix4f scale = new Matrix4f().scale(256);
        Matrix4f target = new Matrix4f();
        projection.mul(scale, target);


		while(!glfwWindowShouldClose(WINDOW)){ //While window is not closed

			keyboardHandler.checkKeyboardInput(); //Check keyboard input //Per Class needs to be ran
			mouseHandler.checkMouseInput(); //Check mouse input

            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT); //Clears the display

            shaderHandler.bind();
            shaderHandler.setUniform("sampler", 0);
            shaderHandler.setUniform("projection", target);
            sampleTexture.bind(0); //Bind the texture to the model

            model.render(); //Render the model

            glfwSwapBuffers(WINDOW); //Swaps the buffer to you can draw to it with OpenGL

		}
		glfwTerminate(); //Terminating program
	}

	public static void main(String[] args) {
		System.out.println("Game Launched");
		mainGameLoop();
		System.out.println("Game Exited");
	}
}