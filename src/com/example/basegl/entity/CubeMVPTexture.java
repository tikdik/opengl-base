package com.example.basegl.entity;

import com.tikdik.opengl.shader.BaseShader;
import com.tikdik.opengl.shader.TextureMVPShader;
import com.tikdik.opengl.utils.MatrixState;
import android.opengl.GLES20;
import android.opengl.Matrix;

public class CubeMVPTexture extends CubeWithTexture{
    @Override
    public void changeModel() {
        Matrix.setIdentityM(matrixModel, 0);
        Matrix.scaleM(matrixModel, 0, 1.5f, 1.5f, 1);
        Matrix.rotateM(MatrixState.getViewMatrxi(), 0, (float)(2 * Math.PI / (2000 / 16)), 0f, 1f, 0f);
    }
    @Override
    BaseShader createShader() {
        return new TextureMVPShader();
    }
    @Override
    public void bindData() {
        super.bindData();
        GLES20.glUniformMatrix4fv(((TextureMVPShader)shader).getUniMVPMatrixLocation(), 1, false, MatrixState.getMVPMatrix(matrixModel), 0);
    }
}
