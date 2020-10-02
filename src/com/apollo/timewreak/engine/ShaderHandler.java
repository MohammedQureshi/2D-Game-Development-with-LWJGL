package com.apollo.timewreak.engine;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.*;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;


public class ShaderHandler {

    private int application;
    private int vertexShader;
    private int fragmentShader;

    public ShaderHandler(String vertexShaderFileName, String fragmentShaderFileName){
        application = glCreateProgram();
        vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, readFile(vertexShaderFileName + ".vs"));
        glCompileShader(vertexShader);
        if(glGetShaderi(vertexShader, GL_COMPILE_STATUS) != 1){
            System.err.println(glGetShaderInfoLog(vertexShader));
            System.exit(1);
        }

        fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, readFile(fragmentShaderFileName + ".fs"));
        glCompileShader(fragmentShader);
        if(glGetShaderi(fragmentShader, GL_COMPILE_STATUS) != 1){
            System.err.println(glGetShaderInfoLog(fragmentShader));
            System.exit(1);
        }

        glAttachShader(application, vertexShader);
        glAttachShader(application, fragmentShader);

        glBindAttribLocation(application, 0, "vertices");
        glBindAttribLocation(application, 1, "textures");
        glLinkProgram(application);
        if(glGetProgrami(application, GL_LINK_STATUS) != 1){
            System.err.println(glGetProgramInfoLog(application));
            System.exit(1);
        }
        glValidateProgram(application);
        if(glGetProgrami(application, GL_VALIDATE_STATUS) != 1){
            System.err.println(glGetProgramInfoLog(application));
            System.exit(1);
        }

    }

    public void setUniform(String name, int value){
        int location = glGetUniformLocation(application, name);
        if(location != -1){
            glUniform1i(location, value);
        }
    }

    public void setUniform(String name, Matrix4f value){
        int location = glGetUniformLocation(application, name);
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        value.get(buffer);
        if(location != -1){
            glUniformMatrix4fv(location, false, buffer);
        }
    }

    public void bind(){
        glUseProgram(application);
    }

    private String readFile(String fileName){
        StringBuilder string = new StringBuilder();
        BufferedReader bufferedReader;
        try{
            bufferedReader = new BufferedReader(new FileReader(new File("./shaders/"+fileName)));
            String line;
            while((line = bufferedReader.readLine()) != null){
                string.append(line);
                string.append("\n");
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return string.toString();
    }
}
