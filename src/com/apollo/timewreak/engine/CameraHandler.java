package com.apollo.timewreak.engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class CameraHandler {

    private Vector3f position;
    private Matrix4f projection;
    
    //Camera object to load camera into main
    public CameraHandler(final int width, final int height){
        position = new Vector3f(0,0,0); //Sets starting position off camera
        projection = new Matrix4f().setOrtho2D(-width/2, width/2, -height/2, height/2); //Sets projection matrix
    }
    
    public void setPosition(final Vector3f position){
        this.position = position; //Set position in another class
    }

    public void addPosition(final Vector3f position){
        this.position.add(position); //Add value to current position
    }

    public Vector3f getPosition(){
        return position; //Get position camera is currently at
    }

    public Matrix4f getProjection(){
        return projection.translate(position, new Matrix4f()); //Get projection matrix
    }

}
