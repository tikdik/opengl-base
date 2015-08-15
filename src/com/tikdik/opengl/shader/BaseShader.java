package com.tikdik.opengl.shader;

import android.opengl.GLES20;

import com.tikdik.opengl.utils.GLHelper;
import com.tikdik.opengl.utils.ShaderProgramHelper;

public class BaseShader {
    public static final String A_POSITION = "a_Position";
    int attrPositionLocation;
    int programHandler;
    public int getAttrPositionLocation() {
        return attrPositionLocation;
    }
    public int createShader(String vertex_source, String fragment_source) {
        programHandler = ShaderProgramHelper.createProgram(vertex_source, fragment_source);
        attrPositionLocation = GLES20.glGetAttribLocation(programHandler, A_POSITION);
        GLHelper.checkGlError("glGetAttribLocation");
        return programHandler;
    }
    public String getVertexShaderFileName() {
        return "points_vertex_shader.glsl";
    }
    public String getFragmentShaderFileName() {
        return "points_fragment_shader.glsl";
    }
    public void useProgram() {
        GLES20.glUseProgram(programHandler);
    }
}
