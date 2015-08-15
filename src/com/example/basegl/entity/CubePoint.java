package com.example.basegl.entity;

import java.nio.FloatBuffer;

import android.opengl.GLES20;

import com.tikdik.context.GlobalContext;
import com.tikdik.opengl.shader.BaseShader;
import com.tikdik.opengl.utils.BufferUtils;
import com.tikdik.opengl.utils.GLHelper;
import com.tikdik.res.ResHelper;

public class CubePoint {
    float matrixModel[] = new float[16];
    //1.vertex data
    float vertexs[] = {
            -0.5f,0.5f,0.0f,
            -0.5f,-0.5f,0.0f,
            0.5f,0.5f,0.0f,
            0.5f,-0.5f,0.0f,
    };
    FloatBuffer vertexBuffer;
    BaseShader shader;
    //2.shader
    public CubePoint() {
        vertexBuffer = BufferUtils.allocateDirectFloatBuffer(vertexs.length);
        vertexBuffer.put(vertexs);
        vertexBuffer.position(0);
        shader = createShader();
    }
    BaseShader createShader() {
        return new BaseShader();
    }
    public void initGLEnv() {
        shader.createShader(ResHelper.loadFromAssetsFile(shader.getVertexShaderFileName()),
                ResHelper.loadFromAssetsFile(shader.getFragmentShaderFileName()));
    }
    /**
     * draw
     * 1.use program
     * 2.bind data
     * 3.draw array or elements
     */
    public void draw() {
        shader.useProgram();
        changeModel();
        bindData();
        GLES20.glDrawArrays(getDrawGLMode(), 0, vertexs.length / 3);
    }
    public void changeModel() {}//for model transform
    public void bindData() {
        GLES20.glVertexAttribPointer(shader.getAttrPositionLocation(), 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);
        GLHelper.checkGlError("glVertexAttribPointer");
        GLES20.glEnableVertexAttribArray(shader.getAttrPositionLocation());
        GLHelper.checkGlError("glEnableVertexAttribArray");
    }
    public int getDrawGLMode() {
        return GLES20.GL_POINTS;
    }
}
