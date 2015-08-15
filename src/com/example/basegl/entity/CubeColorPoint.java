package com.example.basegl.entity;

import java.nio.FloatBuffer;

import android.opengl.GLES20;

import com.tikdik.opengl.shader.BaseColorShader;
import com.tikdik.opengl.shader.BaseShader;
import com.tikdik.opengl.utils.BufferUtils;
import com.tikdik.opengl.utils.GLHelper;

public class CubeColorPoint extends CubePoint {
    //1.vertex color
    float vertexColors[] = {
            1.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
    };
    FloatBuffer vertexColorBuffer;
    public CubeColorPoint() {
        super();
        vertexColorBuffer = BufferUtils.allocateDirectFloatBuffer(vertexColors.length);
        vertexColorBuffer.put(vertexColors);
        vertexColorBuffer.position(0);
    }
    @Override
    BaseShader createShader() {
        return new BaseColorShader();
    }
    @Override
    public void bindData() {
        super.bindData();
        GLES20.glVertexAttribPointer(((BaseColorShader)shader).getAttrColorLocation(), 3, GLES20.GL_FLOAT, false, 0, vertexColorBuffer);
        GLHelper.checkGlError("glVertexAttribPointer");
        GLES20.glEnableVertexAttribArray(((BaseColorShader)shader).getAttrColorLocation());
        GLHelper.checkGlError("glEnableVertexAttribArray");
    }
}
