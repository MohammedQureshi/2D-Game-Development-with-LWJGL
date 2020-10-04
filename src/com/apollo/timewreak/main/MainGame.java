package com.apollo.timewreak.main;

import com.apollo.timewreak.engine.*;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;

public class MainGame {

//	private static KeyboardHandler keyboardHandler = new KeyboardHandler();
//	private static MouseHandler mouseHandler = new MouseHandler();

	private static void mainGameLoop(){
        GLFWErrorCallback.createPrint(System.err).set(); // will print the error message in System.err.
        if(!glfwInit()){ //Check to see if GLFW has been initialised
            System.err.println("GLFW Failed to Initialise"); //Prints Error
            System.exit(1); // Exits System
        }


        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will not be resizable
        DisplayHandler display = new DisplayHandler();
        display.createDisplay("Project Apollo");

        GL.createCapabilities(); //Creates Context

        GL.createCapabilities();
        //GLUtil.setupDebugMessageCallback(); //Debug Checker

        CameraHandler camera = new CameraHandler(display.getWIDTH(), display.getHEIGHT());
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

        //Matrix
        Matrix4f scale = new Matrix4f()
                .translate(new Vector3f(0,0, 0))
                .scale(256);
        Matrix4f target = new Matrix4f();

        camera.setPosition(new Vector3f(100, 0, 0));

        double MAX_FRAME_RATE = 1.0/144.0;
        double FRAME_TIME = 0;
        double FRAMES = 0;
        double time = TimerHandler.getTime();
        double unprocessed = 0;
		while(!display.shouldClose()){ //While window is not closed

		    boolean canRender = false;
		    double time2 = TimerHandler.getTime();
		    double passed = time2 - time;
		    unprocessed += passed;
		    FRAME_TIME += passed;

		    time = time2;
		    while(unprocessed >= MAX_FRAME_RATE){
		        unprocessed -= MAX_FRAME_RATE;
                canRender = true;
                target = scale;
//                keyboardHandler.checkKeyboardInput(); //Check keyboard input //Per Class needs to be ran
//                mouseHandler.checkMouseInput(); //Check mouse input
                camera.setPosition(new Vector3f(0, 0, 0));
                glfwPollEvents();
                if(FRAME_TIME >= 1.0){
                    FRAME_TIME = 0;
                    System.out.println("FPS: " + FRAMES);
                    FRAMES = 0;
                }
            }

		    if(canRender){
                glClear(GL_COLOR_BUFFER_BIT); //Clears the display
                shaderHandler.bind();
                shaderHandler.setUniform("sampler", 0);
                shaderHandler.setUniform("projection", camera.getProjection().mul(target));
                sampleTexture.bind(0); //Bind the texture to the model
                model.render(); //Render the model
                display.swapBuffers();
                FRAMES++;
            }
		}
		glfwTerminate(); //Terminating program
	}

	public static void main(String[] args) {
		System.out.println("Game Launched");
		mainGameLoop();
		System.out.println("Game Exited");
	}
}