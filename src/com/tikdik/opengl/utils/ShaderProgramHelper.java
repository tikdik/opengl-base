package com.tikdik.opengl.utils;

import android.opengl.GLES20;
import android.util.Log;

public class ShaderProgramHelper {
    private static final String TAG = ShaderProgramHelper.class.getSimpleName();
    /*
     * get shader base on type
     * 1.create shader 
     * 2.attach source code 
     * 3.compile
     */
    public static int loadShader(int shaderType, String source) {
        int shader = GLES20.glCreateShader(shaderType);
        if (shader == 0) {
            Log.d(TAG, "can't create new shader " + shaderType);
            return 0;
        }
        GLES20.glShaderSource(shader, source);
        GLES20.glCompileShader(shader);
        int params[] = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, params, 0);
        if (params[0] == 0) {
            Log.d(TAG, "compile shader failed\n" + GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);
            return 0;
        }
        return shader;
    }
    /*
     * get program
     * 1.create program
     * 2.attach program
     * 3.link program
     */
    public static int createProgram(String vertex_source, String fragment_source) {
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertex_source);
        if (vertexShader == 0) {
            throw new RuntimeException("load vertex_source failed!");
        }
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragment_source);
        if (fragmentShader == 0) {
            throw new RuntimeException("load vertex_source failed!");
        }
        int program = GLES20.glCreateProgram();
        if (program == 0) {
            Log.e(TAG, "failed to create new program");
            return 0;
        }
        GLES20.glAttachShader(program, vertexShader);
        GLHelper.checkGlError("glAttachShader");
        GLES20.glAttachShader(program, fragmentShader);
        GLHelper.checkGlError("glAttachShader");
        GLES20.glLinkProgram(program);
        int params[] = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, params, 0);
        if (params[0] == 0) {
            Log.e(TAG, "can't link program\n" + GLES20.glGetProgramInfoLog(program));
            GLES20.glDeleteProgram(program);
            return 0;
        }
        return program;
    }
}
