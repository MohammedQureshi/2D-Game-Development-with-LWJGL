package com.apollo.timewreak.engine;

import java.io.*;
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
