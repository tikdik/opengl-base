package com.example.basegl.entity;

import java.nio.FloatBuffer;

import com.tikdik.opengl.shader.BaseMVPShader;
import com.tikdik.opengl.shader.BaseShader;
import com.tikdik.opengl.shader.TextureLightShader;
import com.tikdik.opengl.utils.BufferUtils;
import com.tikdik.opengl.utils.GLHelper;
import com.tikdik.opengl.utils.MatrixState;
import com.tikdik.res.ResHelper;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.SystemClock;

public class CubeLightTexture extends CubeWithTexture{
    //1.normal data
    float normals[];
    public float[] getNormals() {
        return new float[] {
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
        };
    }
    FloatBuffer normalBuffer;
    final float lightInModelSpace[] = new float[] {
            0.0f, 0.0f, 0.0f, 1.0f
    };
    FloatBuffer lightBuffer;
    private final float[] lightInWorldSpace = new float[4];
    private final float[] lightInEyeSpace = new float[4];
    float[] lightModelMatrix = new float[16];
    public CubeLightTexture() {
        super();
        normals = getNormals();
        normalBuffer = BufferUtils.allocateDirectFloatBuffer(normals.length);
        normalBuffer.put(normals);
        normalBuffer.position(0);
        lightBuffer = BufferUtils.allocateDirectFloatBuffer(lightInModelSpace.length);
        lightBuffer.put(lightInModelSpace);
        lightBuffer.position(0);
    }
    float angleInDegrees = 0;
    @Override
    public void changeModel() {
        Matrix.setIdentityM(matrixModel, 0);
        Matrix.rotateM(matrixModel, 0, angleInDegrees, 0.0f, 1.0f, 0.0f);
        angleInDegrees += 2* Math.PI / 16 ;
    }
    @Override
    BaseShader createShader() {
        return new TextureLightShader();
    }
    BaseMVPShader lightShader;
    @Override
    public void initGLEnv() {
        super.initGLEnv();
        lightShader = new BaseMVPShader();
        lightShader.createShader(ResHelper.loadFromAssetsFile(lightShader.getVertexShaderFileName()),
                ResHelper.loadFromAssetsFile(lightShader.getFragmentShaderFileName()));
    }
    @Override
    public void bindData() {
        super.bindData();
        GLES20.glUniformMatrix4fv(((TextureLightShader)shader).getUniMVPMatrixLocation(), 1, false, MatrixState.getMVPMatrix(matrixModel), 0);
        GLES20.glUniformMatrix4fv(((TextureLightShader)shader).getUniMVLocation(), 1, false, MatrixState.getMVMatrix(matrixModel), 0);
        GLES20.glUniform3f(((TextureLightShader)shader).getUniLightPositionLocation(), lightInEyeSpace[0], lightInEyeSpace[1], lightInEyeSpace[2]);
        normalBuffer.position(0);
        GLES20.glVertexAttribPointer(((TextureLightShader)shader).getAttrNormalLocation(), 3, GLES20.GL_FLOAT, false, 0, normalBuffer);
        GLES20.glEnableVertexAttribArray(((TextureLightShader)shader).getAttrNormalLocation());
    }
    @Override
    public void draw() {
        super.draw();
        drawLight();
    }
    void drawLight() {
        long time = SystemClock.uptimeMillis() % 10000L;
        float angleInDegrees = (360.0f / 10000.0f) * ((int) time);
        float tx = (float) Math.sin(Math.PI / 180.0f * angleInDegrees);
        Matrix.setIdentityM(lightModelMatrix, 0);
        Matrix.translateM(lightModelMatrix, 0, 0, 0.0f, 3f);
        //Matrix.translateM(lightModelMatrix, 0, tx, 0.0f, 0.5f);
        Matrix.multiplyMV(lightInWorldSpace, 0, lightModelMatrix, 0, lightInModelSpace, 0);
        Matrix.multiplyMV(lightInEyeSpace, 0, MatrixState.getViewMatrxi(), 0, lightInWorldSpace, 0);
        lightShader.useProgram();
        float mvp[] = MatrixState.getMVPMatrix(lightModelMatrix);
        GLES20.glUniformMatrix4fv(lightShader.getUniMVPMatrixLocation(), 1, false, mvp, 0);
        GLHelper.checkGlError("glUniformMatrix4fv");
        GLES20.glVertexAttribPointer(lightShader.getAttrPositionLocation(), 4, GLES20.GL_FLOAT, false, 0, lightBuffer);
        GLHelper.checkGlError("glVertexAttribPointer");
        GLES20.glEnableVertexAttribArray(lightShader.getAttrPositionLocation());
        GLHelper.checkGlError("glEnableVertexAttribArray");
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, lightInModelSpace.length / 4);
    }
}
