package com.apollo.timewreak.engine;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.opengl.GL20.*;

public class ModelHandler {

    private int drawCount;
    private int vertexID;
    private int textureID;

    private int indicesID;

    public ModelHandler(final float[] vertices, final float[] textureCoords, final int[] indices){
        drawCount = indices.length; //Assigns the draw count to the length of the indices such as how drawing the object

        vertexID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexID);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices),GL_STATIC_DRAW);
        textureID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, textureID);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(textureCoords), GL_STATIC_DRAW);

        indicesID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesID);
        IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
        buffer.put(indices);
        buffer.flip();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    protected void finalise(){
    	//Deletes all buffers when not visible so game does not lag. 
        glDeleteBuffers(vertexID);
        glDeleteBuffers(textureID);
        glDeleteBuffers(indicesID);
    }

    public void render(){
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, vertexID);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, textureID);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesID);
        glDrawElements(GL_TRIANGLES, drawCount, GL_UNSIGNED_INT, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
    }

    private FloatBuffer createBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

}
