package com.example.basegl.entity;

import android.opengl.GLES20;

public class CubeColor extends CubeColorPoint {
    @Override
    public int getDrawGLMode() {
        return GLES20.GL_TRIANGLE_STRIP;
    }
}
