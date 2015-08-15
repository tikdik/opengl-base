package com.tikdik.opengl.shader;

import android.opengl.GLES20;

import com.tikdik.opengl.utils.GLHelper;
import com.tikdik.opengl.utils.ShaderProgramHelper;

public class TextureShader extends BaseShader{
    public static final String A_TEXTURE_POSITION = "a_TexturePosition";
    public static final String S_TEXTURE = "s_Texture";
    int attrTexturePositionLocation;
    int uniTextureLocation;
    public int createShader(String vertex_source, String fragment_source) {
        super.createShader(vertex_source, fragment_source);
        attrTexturePositionLocation = GLES20.glGetAttribLocation(programHandler, A_TEXTURE_POSITION);
        GLHelper.checkGlError("glGetAttribLocation");
        uniTextureLocation = GLES20.glGetUniformLocation(programHandler, S_TEXTURE);
        GLHelper.checkGlError("glGetUniformLocation");
        return programHandler;
    }
    public String getVertexShaderFileName() {
        return "texture_vertex_shader.glsl";
    }
    public String getFragmentShaderFileName() {
        return "texture_fragment_shader.glsl";
    }
    public int getAttrTexturePositionLocation() {
        return attrTexturePositionLocation;
    }
    public int getUniTextureLocation() {
        return uniTextureLocation;
    }
}
