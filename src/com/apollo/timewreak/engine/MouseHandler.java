package com.apollo.timewreak.engine;

import com.apollo.timewreak.main.MainGame;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class MouseHandler {

    private static MainGame mainGame = new MainGame();

    private DoubleBuffer xMouse = BufferUtils.createDoubleBuffer(1);
    private DoubleBuffer yMouse = BufferUtils.createDoubleBuffer(1);
    public void checkMouseInput(){
        glfwGetCursorPos(mainGame.WINDOW, xMouse, yMouse);
        if(glfwGetMouseButton(mainGame.WINDOW, GLFW_MOUSE_BUTTON_2) == GL_TRUE){
            System.out.println("x : " + xMouse.get(0) + ", y = " + yMouse.get(0));
        }
    }

    public void checkMouseOver(){
//        if(xMouse.get(0) >= texture.getX && xMouse.get(0) >= texture.getWidth()){
//            if(yMouse.get(0) >= texture.getY && yMouse.get(0) >= texture.getHeight()){
//                System.out.println("Mouse over texture");
//            }
//        }
    }

}
