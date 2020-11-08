package com.apollo.timewreak.entity;

import com.apollo.timewreak.engine.CameraHandler;
import com.apollo.timewreak.engine.ModelHandler;
import com.apollo.timewreak.engine.ShaderHandler;
import com.apollo.timewreak.engine.TextureHandler;
import com.apollo.timewreak.inputOutput.DisplayHandler;
import com.apollo.timewreak.main.Config;
import com.apollo.timewreak.world.World;
import org.joml.Vector3f;
import org.w3c.dom.Text;

import java.util.Vector;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

public class Player {
    private ModelHandler model;
    private TextureHandler texture;
    private Transform transform;
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
        this.texture = new TextureHandler("front_left.png");

        transform = new Transform();
        float Scale = 16;
        transform.scale = new Vector3f(Scale,Scale,1);
    }

    public void update(float delta, DisplayHandler display, CameraHandler camera, World world){
        if(display.getInput().isKeyDown(GLFW_KEY_W)){
            transform.position.add(new Vector3f(0, Config.CAMERA_SPEED*delta, 0));
        }
        if(display.getInput().isKeyDown(GLFW_KEY_A)){
            transform.position.add(new Vector3f(-Config.CAMERA_SPEED*delta, 0, 0));
        }
        if(display.getInput().isKeyDown(GLFW_KEY_S)){
            transform.position.add(new Vector3f(0, -Config.CAMERA_SPEED*delta, 0));
        }
        if(display.getInput().isKeyDown(GLFW_KEY_D)){
            transform.position.add(new Vector3f(Config.CAMERA_SPEED*delta, 0, 0));
        }

        camera.setPosition(transform.position.mul(-world.getScale(), new Vector3f()));
    }

    public void render(ShaderHandler shader, CameraHandler camera){
        shader.bind();
        shader.setUniform("sampler", 0);
        shader.setUniform("projection", transform.getProjection(camera.getProjection()));
        texture.bind(0);
        model.render();
    }

}
