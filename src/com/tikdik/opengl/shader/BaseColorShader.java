package com.tikdik.opengl.shader;

import android.opengl.GLES20;

import com.tikdik.opengl.utils.GLHelper;
import com.tikdik.opengl.utils.ShaderProgramHelper;

public class BaseColorShader extends BaseShader{
    public static final String A_COLOR = "a_Color";
    int attrColorLocation;
    public int getAttrColorLocation() {
        return attrColorLocation;
    }
    public int createShader(String vertex_source, String fragment_source) {
        super.createShader(vertex_source, fragment_source);
        attrColorLocation = GLES20.glGetAttribLocation(programHandler, A_COLOR);
        GLHelper.checkGlError("glGetAttribLocation");
        return programHandler;
    }
    public String getVertexShaderFileName() {
        return "points_color_vertex_shader.glsl";
    }
    public String getFragmentShaderFileName() {
        return "points_color_fragment_shader.glsl";
    }
}
