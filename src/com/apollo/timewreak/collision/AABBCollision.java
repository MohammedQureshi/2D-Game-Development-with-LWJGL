package com.apollo.timewreak.collision;

import org.joml.Vector2f;

public class AABBCollision {

    private Vector2f centre, halfExtent;

    public AABBCollision(Vector2f centre, Vector2f halfExtent){
        this.centre = centre;
        this.halfExtent = halfExtent;
    }

    public Collision getCollision(AABBCollision boxTwo){
        Vector2f distance = boxTwo.centre.sub(centre, new Vector2f());
        distance.x = (float) Math.abs(distance.x);
        distance.y = (float) Math.abs(distance.y);
        distance.sub(halfExtent.add(boxTwo.halfExtent, new Vector2f()));
        return new Collision(distance,distance.x < 0 && distance.y < 0);
    }

    public void correctPosition(AABBCollision boxTwo, Collision data){
        Vector2f correction = boxTwo.centre.sub(centre, new Vector2f());
        if(data.distance.x > data.distance.y){
            if(correction.x > 0){
                centre.add(data.distance.x, 0);
            }else{
                centre.add(-data.distance.x, 0);
            }
        }else{
            if(correction.y > 0){
                centre.add(0, data.distance.y);
            }else{
                centre.add(0, -data.distance.y);
            }
        }
    }

    public Vector2f getCentre(){return centre;}
    public Vector2f getHalfExtent(){ return halfExtent;}
}
