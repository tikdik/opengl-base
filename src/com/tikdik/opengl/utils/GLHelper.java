package com.tikdik.opengl.utils;

import android.opengl.GLES20;
import android.util.Log;

public class GLHelper {
    public static void checkGlError(String op) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e("GLES20_ERROR", op + ": glError " + error);
            throw new RuntimeException(op + ": glError " + error);
        }
    }
}
