package com.apollo.timewreak.main;

import com.apollo.timewreak.collision.AABBCollision;
import com.apollo.timewreak.engine.*;
import com.apollo.timewreak.entity.Player;
import com.apollo.timewreak.inputOutput.DisplayHandler;
import com.apollo.timewreak.inputOutput.TimerHandler;
import com.apollo.timewreak.world.TileHandler;
import com.apollo.timewreak.world.TileRenderer;
import com.apollo.timewreak.world.World;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;

public class MainGame {

	private static void mainGameLoop(){
        GLFWErrorCallback.createPrint(System.err).set(); // will print the error message in System.err.
        if(!glfwInit()){ //Check to see if GLFW has been initialised
            System.err.println("GLFW Failed to Initialise"); //Prints Error
            System.exit(1); // Exits System
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will not be resizable
        DisplayHandler display = new DisplayHandler();
        display.createDisplay(Config.GAME_NAME);

        GL.createCapabilities(); //Creates Context

        //GLUtil.setupDebugMessageCallback(); //Debug Checker
        CameraHandler camera = new CameraHandler(display.getWIDTH(), display.getHEIGHT());
        glEnable(GL_TEXTURE_2D); //Enable 2D Textures
        glEnable(GL_BLEND); //Enable Blending
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //Allow Alpha channel

        TileRenderer tiles = new TileRenderer();

        ShaderHandler shaderHandler = new ShaderHandler("VertexShader", "FragmentShader");
        //TextureHandler sampleTexture = new TextureHandler("RoomBackground.png"); //Load Texture

        World world = new World();

        Player player = new Player();

        world.setTile(TileHandler.SandTile, 2, 2);
        world.setTile(TileHandler.StoneTile, 3, 3);
        world.setTile(TileHandler.StoneTile, 4, 4);
        world.setTile(TileHandler.StoneTile, 5, 5);

        double MAX_FRAME_RATE = 1.0/Config.MAX_FPS;
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
                if(display.getInput().isKeyReleased(GLFW_KEY_ESCAPE)){
                    glfwSetWindowShouldClose(display.getWindow(), true);
                }

                player.update((float)MAX_FRAME_RATE, display, camera, world);

                world.correctCamera(camera, display);
                display.update();

                if(FRAME_TIME >= 1.0){
                    FRAME_TIME = 0;
                    System.out.println("FPS: " + FRAMES);
                    FRAMES = 0;
                }
            }
		    if(canRender){
                glClear(GL_COLOR_BUFFER_BIT); //Clears the display

                world.render(tiles, shaderHandler, camera, display);
                player.render(shaderHandler, camera);
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