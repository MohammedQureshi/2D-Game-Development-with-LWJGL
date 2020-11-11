package com.apollo.timewreak.main;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class GameObject {
	/** The Position of the current GameObject. */
	private Vector3f position;

	/**
	 *	@param x the x position
	 *	@param y the y position
	 *	@param z the z position
	 */
	public GameObject(final float x, final float y, final float z) {
		this.position = new Vector3f(x, y, z);
	}

	/** Sets the x and y position of the GameObject */
	public GameObject(final float x, final float y) {
		this(x, y, 0);
	}

	/** Sets the x, y and z position of the GameObject to be the same */
	public GameObject(final float startPos) {
		this(startPos, startPos, startPos);
	}

	/** Sets the x, y and z Position to be 0 by default. */
	public GameObject() {
		this(0, 0,0);
	}

	/** Returns the Vector3f position of the current GameObject. */
	public Vector3f getPosition() { return position; }

	/** Returns the GameObjects X Position */
	public float getX() { return this.position.x; }

	/** Returns the GameObjects Y Position */
	public float getY() { return this.position.y; }

	/** Returns the GameObjects Z Position */
	public float getZ() { return this.position.z; }

	/**
	 * Sets the Players X position
	 * @param x the X position you want to set the GameObject to
	 */
	public void setX(final float x) { this.position.x = x; }

	/**
	 * Sets the Players Y position
	 * @param y the Y position you want to set the GameObject to
	 */
	public void setY(final float y) { this.position.y = y; }

	/**
	 * Sets the Players Z position
	 * @param z the Z position you want to set the GameObject to
	 */
	public void setZ(final float z) { this.position.z = z; }
}
