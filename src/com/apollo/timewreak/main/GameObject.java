package com.apollo.timewreak.main;

import org.joml.Vector3f;

public class GameObject {
	
	private float xPosition = 0;
	private float yPosition = 0;
	
	public GameObject(float x, float y) {
		this.xPosition = x;
		this.yPosition = y;
	}
	
	public Vector3f getPlayerLocation() {
		 return new Vector3f(xPosition, yPosition, 1);
	}

}
