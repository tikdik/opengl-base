package com.tikdik.opengl.shader;

import android.opengl.GLES20;

import com.tikdik.opengl.utils.GLHelper;
import com.tikdik.opengl.utils.ShaderProgramHelper;

public class TextureMVPShader extends TextureShader{
    public static final String U_MVP_MATRIX = "u_MVPMatrix";
    int uniMVPMatrixLocation;
    public int createShader(String vertex_source, String fragment_source) {
        super.createShader(vertex_source, fragment_source);
        uniMVPMatrixLocation = GLES20.glGetUniformLocation(programHandler, U_MVP_MATRIX);
        GLHelper.checkGlError("glGetUniformLocation");
        return programHandler;
    }
    public String getVertexShaderFileName() {
        return "texture_mvp_vertex_shader.glsl";
    }
    public String getFragmentShaderFileName() {
        return "texture_fragment_shader.glsl";
    }
    public int getUniMVPMatrixLocation() {
        return uniMVPMatrixLocation;
    }
}
