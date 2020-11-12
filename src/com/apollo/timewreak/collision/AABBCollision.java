package com.apollo.timewreak.collision;

import org.joml.Vector2f;

public class AABBCollision {

    private Vector2f centre, halfExtent;

    public AABBCollision(Vector2f centre, Vector2f halfExtent){
        this.centre = centre;
        this.halfExtent = halfExtent;
    }

    public boolean isIntersecting(AABBCollision boxTwo){
        Vector2f distance = boxTwo.centre.sub(centre, new Vector2f());
        distance.x = (float) Math.abs(distance.x);
        distance.y = (float) Math.abs(distance.y);
        distance.sub(halfExtent.add(boxTwo.halfExtent, new Vector2f()));
        return (distance.x < 0 && distance.y < 0);
    }

    public Vector2f getCentre(){return centre;}
    public Vector2f getHalfExtent(){ return halfExtent;}
}
