package com.example.basegl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.example.basegl.entity.CubeColorLine;
import com.example.basegl.entity.CubePoint;
import com.tikdik.opengl.utils.MatrixState;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;

public class CubeRender implements Renderer{
    CubePoint cubePoint;
    public void setCubeShape(CubePoint cubeShape) {
        cubePoint = cubeShape;
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        if (cubePoint != null)
            cubePoint.initGLEnv();
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = ((float) width) / height;
        Matrix.frustumM(MatrixState.getProjectMatrix(), 0, -ratio, ratio, -1, 1, 25, 100);
        Matrix.setLookAtM(MatrixState.getViewMatrxi(), 0, 0f, 0f, 45f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        Matrix.multiplyMM(MatrixState.getVPMatrix(), 0, MatrixState.getProjectMatrix(), 0, MatrixState.getViewMatrxi(), 0);
    }
    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        if (cubePoint != null)
            cubePoint.draw();
    }
}
