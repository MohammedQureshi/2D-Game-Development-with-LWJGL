package com.apollo.timewreak.entity;

import com.apollo.timewreak.engine.*;
import com.apollo.timewreak.inputOutput.DisplayHandler;
import com.apollo.timewreak.main.Config;
import com.apollo.timewreak.main.GameObject;
import com.apollo.timewreak.world.World;
import org.joml.Vector3f;
import org.w3c.dom.Text;

import java.util.Vector;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

public class Player{
    private ModelHandler model;
    private AnimationHandler texture;
    private Transform transform;
    private GameObject object;
    public Player(){
        float[] vertices = new float[]{
                -1f, 1f, 0, //Top Left 0
                1f, 1f, 0, //Top Right 1
                1f, -1f, 0, //Bottom Right 2
                -1f, -1f, 0, //Bottle Left 3
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

        model = new ModelHandler(vertices, texture, indices);
        this.texture = new AnimationHandler(1, 3, "frontLeft");

        transform = new Transform();
        float Scale = 64;
        transform.scale = new Vector3f(Scale,Scale,1);
    }
    private boolean walkingUp = false;
    private boolean walkingRight = false;
    public void update(float delta, DisplayHandler display, CameraHandler camera, World world){
        if(display.getInput().isKeyDown(GLFW_KEY_W)){
            transform.position.add(new Vector3f(0, Config.CAMERA_SPEED*delta, 0));
            walkingUp = true;
        }
        if(display.getInput().isKeyDown(GLFW_KEY_A)){
            transform.position.add(new Vector3f(-Config.CAMERA_SPEED*delta, 0, 0));
            walkingRight = false;
        }
        if(display.getInput().isKeyDown(GLFW_KEY_S)){
            transform.position.add(new Vector3f(0, -Config.CAMERA_SPEED*delta, 0));
            walkingUp = false;
        }
        if(display.getInput().isKeyDown(GLFW_KEY_D)){
            transform.position.add(new Vector3f(Config.CAMERA_SPEED*delta, 0, 0));
            walkingRight = true;
        }

        if(walkingUp == true && walkingRight == false){
            this.texture = new AnimationHandler(1, 3, "backLeft");
        } else if(walkingUp == true && walkingRight == true){
            this.texture = new AnimationHandler(1, 3, "backRight");
        }

        if(walkingUp == false && walkingRight == false){
            this.texture = new AnimationHandler(3, 3, "frontLeft");
        } else if(walkingUp == false && walkingRight == true){
            this.texture = new AnimationHandler(3, 3, "frontRight");
        }
        
        object = new GameObject(transform.getPosition().x,transform.getPosition().y);

        camera.setPosition(transform.position.mul(-world.getScale() -50, new Vector3f()));
    }

    public void render(ShaderHandler shader, CameraHandler camera){
        shader.bind();
        shader.setUniform("sampler", 0);
        shader.setUniform("projection", transform.getProjection(camera.getProjection()));
        texture.bind(0);
        model.render();
    }

}
