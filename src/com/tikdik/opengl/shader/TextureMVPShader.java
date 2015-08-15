package com.tikdik.opengl.shader;

import android.opengl.GLES20;

import com.tikdik.opengl.utils.GLHelper;
import com.tikdik.opengl.utils.ShaderProgramHelper;

public class TextureMVPShader extends TextureShader{
    public static final String U_MODEL_MATRIX = "u_ModelMatrix";
    public static final String U_VP_MATRIX = "u_VPMatrix";
    int uniModelLocation;
    int uniVPMatrixLocation;
    public int createShader(String vertex_source, String fragment_source) {
        super.createShader(vertex_source, fragment_source);
        uniModelLocation = GLES20.glGetUniformLocation(programHandler, U_MODEL_MATRIX);
        GLHelper.checkGlError("glGetUniformLocation");
        uniVPMatrixLocation = GLES20.glGetUniformLocation(programHandler, U_VP_MATRIX);
        GLHelper.checkGlError("glGetUniformLocation");
        return programHandler;
    }
    public String getVertexShaderFileName() {
        return "texture_mvp_vertex_shader.glsl";
    }
    public String getFragmentShaderFileName() {
        return "texture_fragment_shader.glsl";
    }
    public int getUniModelLocation() {
        return uniModelLocation;
    }
    public int getUniVPMatrixLocation() {
        return uniVPMatrixLocation;
    }
}
