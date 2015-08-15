package com.example.basegl.entity;

import android.opengl.GLES20;

public class CubeColorLine extends CubeColorPoint {
    @Override
    public int getDrawGLMode() {
        return GLES20.GL_LINE_LOOP;
    }
}
