package com.tikdik.opengl.utils;

import android.opengl.Matrix;

public class MatrixState {
    static float matrixVP[] = new float[16];
    static float matrixProject[] = new float[16];
    static float matrixView[] = new float[16];
    //static float matrixMVP[];
    public static float[] getVPMatrix() {
        return matrixVP;
    }
    public static float[] getProjectMatrix() {
        return matrixProject;
    }
    public static float[] getViewMatrxi() {
        return matrixView;
    }
    public static float[] getMVPMatrix(float[] modelMatrix) {
        float matrixMVP[] = new float[16];
        Matrix.multiplyMM(matrixMVP, 0, matrixView, 0, modelMatrix, 0);
        Matrix.multiplyMM(matrixMVP, 0, matrixProject, 0, matrixMVP, 0);
        return matrixMVP;
    }
    public static float[] getMVMatrix(float[] modelMatrix) {
        float matrixMV[] = new float[16];
        Matrix.multiplyMM(matrixMV, 0, matrixView, 0, modelMatrix, 0);
        return matrixMV;
    }
}
