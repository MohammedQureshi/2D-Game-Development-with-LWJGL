package com.apollo.timewreak.engine;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL46.*;

public class ModelHandler {

    private int drawCount;
    private int vertexID;
    private int textureID;

    public ModelHandler(float[] vertices, float[] textureCoords){
        drawCount = vertices.length / 3;

        vertexID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexID);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices),GL_STATIC_DRAW);
        textureID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, textureID);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(textureCoords), GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void render(){
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
        glBindBuffer(GL_ARRAY_BUFFER, vertexID);
        glVertexPointer(3, GL_FLOAT, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, textureID);
        glTexCoordPointer(2, GL_FLOAT, 0, 0);
        glDrawArrays(GL_TRIANGLES, 0, drawCount);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
    }

    private FloatBuffer createBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

}
