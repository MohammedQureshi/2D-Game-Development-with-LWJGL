package com.apollo.timewreak.entity;

import com.apollo.timewreak.collision.AABBCollision;
import com.apollo.timewreak.collision.Collision;
import com.apollo.timewreak.engine.*;
import com.apollo.timewreak.inputOutput.DisplayHandler;
import com.apollo.timewreak.inputOutput.InputHandler;
import com.apollo.timewreak.main.Config;
import com.apollo.timewreak.main.GameObject;
import com.apollo.timewreak.world.World;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.Vector;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

public class Player{
    private ModelHandler model;
    private AnimationHandler texture;
    private Transform transform;
    private GameObject object;
    private AABBCollision boundingBox;
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
        float Scale = 16; //Standard 64
        transform.scale = new Vector3f(Scale,Scale,1);
        boundingBox = new AABBCollision(new Vector2f(transform.position.x, transform.position.y), new Vector2f(1,1));
    }


    /** amount and fps set to the default fra which is frontLeft. */
    private String facingDirection = "front";
    private String movingDirection = "Left";
    private String previousState = "";
    private int amount = 3;
    private double fps = 3;

    // TODO: think of a better want to do this, probably with animation states or something similar
    public void update(final float delta, final DisplayHandler display, final CameraHandler camera, final World world){
        InputHandler input = display.getInput();
        float x = 0;
        float y = 0;
        float z = 0;

        if(input.isKeyDown(GLFW_KEY_W)){
            y = (Config.PLAYER_SPEED * delta);
            amount = 1;
            fps = 3;
            facingDirection = "back";
        }

        if(input.isKeyDown(GLFW_KEY_A)){
            movingDirection = "Left";
            x = (-Config.PLAYER_SPEED * delta);
        }

        if(input.isKeyDown(GLFW_KEY_S)){
            facingDirection = "front";
            y = (-Config.PLAYER_SPEED * delta);
            amount = 3;
            fps = 3;
        }

        if(input.isKeyDown(GLFW_KEY_D)){
            movingDirection = "Right";
            x = Config.PLAYER_SPEED * delta;
        }

        boundingBox.getCentre().set(transform.position.x, transform.position.y);

        /** updating only when the the player actually updates their movements instead of updating the texture constantly. */
        if(!(facingDirection.concat(movingDirection)).equals(previousState)) {
            this.texture = new AnimationHandler(amount, fps, facingDirection + movingDirection);
            previousState = facingDirection.concat(movingDirection);
        }

        transform.position.add(new Vector3f(x, y, z));

        object = new GameObject(transform.position.x,transform.position.y);

        AABBCollision[] boxes = new AABBCollision[25];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boxes[i + j * 5] = world.getTileBoundingBox(
                        (int)(((transform.position.x /2) + 0.5f) - (5/2)) + i,
                        (int)(((-transform.position.y /2) + 0.5f) - (5/2)) + j
                );
            }
        }

        AABBCollision box = null;
        for(int i = 0; i < boxes.length; i++){
            if(boxes[i] != null){
                if(box == null) box = boxes[i];
                Vector2f lengthOne = box.getCentre().sub(transform.position.x, transform.position.y, new Vector2f());
                Vector2f lengthTwo = boxes[i].getCentre().sub(transform.position.x, transform.position.y, new Vector2f());

                if(lengthOne.lengthSquared() > lengthTwo.lengthSquared()){
                    box = boxes[i];
                }
            }
        }

        if(box != null){
            Collision data = boundingBox.getCollision(box);
            if(data.isIntersecting){
                boundingBox.correctPosition(box, data);
                transform.position.set(boundingBox.getCentre(), 0);
            }

            for(int i = 0; i < boxes.length; i++){
                if(boxes[i] != null){
                    if(box == null) box = boxes[i];
                    Vector2f lengthOne = box.getCentre().sub(transform.position.x, transform.position.y, new Vector2f());
                    Vector2f lengthTwo = boxes[i].getCentre().sub(transform.position.x, transform.position.y, new Vector2f());

                    if(lengthOne.lengthSquared() > lengthTwo.lengthSquared()){
                        box = boxes[i];
                    }
                }
            }
            data = boundingBox.getCollision(box);
            if(data.isIntersecting){
                boundingBox.correctPosition(box, data);
                transform.position.set(boundingBox.getCentre(), 0);
            }
        }

        camera.setPosition(transform.position.mul(-world.getScale(), new Vector3f()));
        //camera.setPosition(transform.position.mul(-world.getScale() -50, new Vector3f())); for scale 64
    }


    public void render(ShaderHandler shader, CameraHandler camera){
        shader.bind();
        shader.setUniform("sampler", 0);
        shader.setUniform("projection", transform.getProjection(camera.getProjection()));
        texture.bind(0);
        model.render();
    }

}
