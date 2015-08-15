package com.example.basegl.entity;

import android.opengl.GLES20;

public class CubeLine extends CubePoint{
    @Override
    public int getDrawGLMode() {
        return GLES20.GL_LINE_LOOP;
    }
}
