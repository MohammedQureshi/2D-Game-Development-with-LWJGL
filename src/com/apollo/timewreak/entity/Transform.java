package com.apollo.timewreak.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.Vector;

public class Transform {
    public Vector3f position;
    public Vector3f scale;

    public Transform(){
        position = new Vector3f();
        scale = new Vector3f(1,1,1);
    }
    
    public Vector3f getPosition() {
    	return position;
    }

    public Matrix4f getProjection(final Matrix4f target){
        target.scale(scale);
        target.translate(position);
        return target;
    }
}
