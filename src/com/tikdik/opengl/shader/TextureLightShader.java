package com.tikdik.opengl.shader;

import android.opengl.GLES20;
import com.tikdik.opengl.utils.GLHelper;

public class TextureLightShader extends TextureShader{
    public static final String U_MV_MATRIX = "u_MVMatrix";
    public static final String U_MVP_MATRIX = "u_MVPMatrix";
    public static final String U_LIGHT_POSITION = "u_LightPosition";
    public static final String A_NORMAL = "a_Normal";
    int uniMVLocation;
    int uniMVPMatrixLocation;
    int uniLightPositionLocation;
    int attrNormalLocation;
    public int createShader(String vertex_source, String fragment_source) {
        super.createShader(vertex_source, fragment_source);
        uniMVLocation = GLES20.glGetUniformLocation(programHandler, U_MV_MATRIX);
        GLHelper.checkGlError("glGetUniformLocation");
        uniMVPMatrixLocation = GLES20.glGetUniformLocation(programHandler, U_MVP_MATRIX);
        GLHelper.checkGlError("glGetUniformLocation");
        uniLightPositionLocation = GLES20.glGetUniformLocation(programHandler, U_LIGHT_POSITION);
        GLHelper.checkGlError("glGetUniformLocation");
        attrNormalLocation = GLES20.glGetAttribLocation(programHandler, A_NORMAL);
        GLHelper.checkGlError("glGetUniformLocation");
        return programHandler;
    }
    public String getVertexShaderFileName() {
        return "light_mvp_vertex_shader.glsl";
    }
    public String getFragmentShaderFileName() {
        return "light_fragment_shader.glsl";
    }
    public int getUniMVLocation() {
        return uniMVLocation;
    }
    public int getUniMVPMatrixLocation() {
        return uniMVPMatrixLocation;
    }
    public int getUniLightPositionLocation() {
        return uniLightPositionLocation;
    }
    public int getAttrNormalLocation() {
        return attrNormalLocation;
    }
}
