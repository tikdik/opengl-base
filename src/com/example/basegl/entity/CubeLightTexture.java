package com.example.basegl.entity;

import java.nio.FloatBuffer;

import com.tikdik.opengl.shader.BaseShader;
import com.tikdik.opengl.shader.TextureLightShader;
import com.tikdik.opengl.utils.BufferUtils;
import com.tikdik.opengl.utils.MatrixState;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.SystemClock;

public class CubeLightTexture extends CubeWithTexture{
    //1.normal data
    float normals[] = {
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
    };
    FloatBuffer normalBuffer;
    final float lightInModelSpace[] = new float[] {
            0.0f, 0.0f, 0.0f, 1.0f
    };
    private final float[] lightInWorldSpace = new float[4];
    private final float[] lightInEyeSpace = new float[4];
    float[] lightModelMatrix = new float[16];
    /** Used to hold a light centered on the origin in model space. We need a 4th coordinate so we can get translations to work when
     *  we multiply this by our transformation matrices. */
    private final float[] mLightPosInModelSpace = new float[] {0.0f, 0.0f, 0.0f, 1.0f};
    
    /** Used to hold the current position of the light in world space (after transformation via model matrix). */
    private final float[] mLightPosInWorldSpace = new float[4];
    
    /** Used to hold the transformed position of the light in eye space (after transformation via modelview matrix) */
    private final float[] mLightPosInEyeSpace = new float[4];
    /** 
     * Stores a copy of the model matrix specifically for the light position.
     */
    private float[] mLightModelMatrix = new float[16];  
    public CubeLightTexture() {
        super();
        normalBuffer = BufferUtils.allocateDirectFloatBuffer(normals.length);
        normalBuffer.put(normals);
        normalBuffer.position(0);
    }
    @Override
    public void changeModel() {
        Matrix.setIdentityM(matrixModel, 0);
        Matrix.scaleM(matrixModel, 0, 1.5f, 1.5f, 1);
        long time = SystemClock.uptimeMillis() % 10000L;
        float angleInDegrees = (360.0f / 10000.0f) * ((int) time);
        Matrix.setIdentityM(lightModelMatrix, 0);
        Matrix.translateM(lightModelMatrix, 0, 1.0f, 1.0f, 0.5f);
        Matrix.rotateM(lightModelMatrix, 0, angleInDegrees, 0f, 0f, 1f);
        Matrix.translateM(lightModelMatrix, 0, 0.0f, 0.0f, -0.1f);
      Matrix.setIdentityM(mLightModelMatrix, 0);
      Matrix.translateM(mLightModelMatrix, 0, 0.0f, 0.0f, -5.0f);      
      Matrix.rotateM(mLightModelMatrix, 0, angleInDegrees, 0.0f, 1.0f, 0.0f);
      Matrix.translateM(mLightModelMatrix, 0, 0.0f, 0.0f, 2.0f);
        Matrix.multiplyMV(mLightPosInWorldSpace, 0, mLightModelMatrix, 0, lightInModelSpace, 0);
        Matrix.multiplyMV(mLightPosInEyeSpace, 0, MatrixState.getViewMatrxi(), 0, mLightPosInWorldSpace, 0);
//        
//        // Calculate position of the light. Rotate and then push into the distance.
//        Matrix.setIdentityM(mLightModelMatrix, 0);
//        Matrix.translateM(mLightModelMatrix, 0, 0.0f, 0.0f, -5.0f);      
//        Matrix.rotateM(mLightModelMatrix, 0, angleInDegrees, 0.0f, 1.0f, 0.0f);
//        Matrix.translateM(mLightModelMatrix, 0, 0.0f, 0.0f, 2.0f);
//               
//        Matrix.multiplyMV(mLightPosInWorldSpace, 0, mLightModelMatrix, 0, mLightPosInModelSpace, 0);
//        Matrix.multiplyMV(mLightPosInEyeSpace, 0, MatrixState.getViewMatrxi(), 0, mLightPosInWorldSpace, 0);
    }
    @Override
    BaseShader createShader() {
        return new TextureLightShader();
    }
    @Override
    public void bindData() {
        super.bindData();
        GLES20.glUniformMatrix4fv(((TextureLightShader)shader).getUniMVPMatrixLocation(), 1, false, MatrixState.getMVPMatrix(matrixModel), 0);
        GLES20.glUniformMatrix4fv(((TextureLightShader)shader).getUniMVLocation(), 1, false, MatrixState.getMVMatrix(matrixModel), 0);
        GLES20.glUniform3f(((TextureLightShader)shader).getUniLightPositionLocation(), mLightPosInEyeSpace[0], mLightPosInEyeSpace[1], mLightPosInEyeSpace[2]);
        GLES20.glVertexAttribPointer(((TextureLightShader)shader).getAttrNormalLocation(), 3, GLES20.GL_FLOAT, false, 0, normalBuffer);
        GLES20.glEnableVertexAttribArray(((TextureLightShader)shader).getAttrNormalLocation());
    }
}
