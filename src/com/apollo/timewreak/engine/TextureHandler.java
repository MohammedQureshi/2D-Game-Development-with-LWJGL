package com.apollo.timewreak.engine;

import org.lwjgl.BufferUtils;
import java.nio.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL13.*;

public class TextureHandler {

    private int textureID;
    private int textureWidth;
    private int textureHeight;

    public TextureHandler(String fileName){
        IntBuffer textureWidth = BufferUtils.createIntBuffer(1);
        IntBuffer textureHeight = BufferUtils.createIntBuffer(1);
        IntBuffer textureComp = BufferUtils.createIntBuffer(1);

        ByteBuffer textureData = stbi_load("./res/" + fileName, textureWidth, textureHeight, textureComp, 4);

        textureID = glGenTextures();
        this.textureWidth = textureWidth.get();
        this.textureHeight = textureHeight.get();

        glBindTexture(GL_TEXTURE_2D, textureID);

        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.textureWidth, this.textureHeight, 0, GL_RGBA, GL_UNSIGNED_BYTE, textureData);
        stbi_image_free(textureData);
    }

    public void bind(int sampler){
        if(sampler >= 0 && sampler <= 31){
            glActiveTexture(GL_TEXTURE0 + sampler);
            glBindTexture(GL_TEXTURE_2D, textureID);
        }
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }
}
