package com.example.basegl.entity;

import com.tikdik.opengl.shader.TextureShader;
import com.tikdik.opengl.utils.FrameBuffer;
import com.tikdik.opengl.utils.GLHelper;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class FBOTexture extends CubeMVPTexture{
    //need a fbo
    FrameBuffer frameBuffer = new FrameBuffer();
    @Override
    public void changeModel() {
        Matrix.setIdentityM(matrixModel, 0);
        Matrix.scaleM(matrixModel, 0, 1.5f, 1.5f, 1);
    }
    @Override
    public void onSurfaceSizeChange(int width, int height) {
        super.onSurfaceSizeChange(width, height);
        frameBuffer.init(width, height);
    }
    @Override
    public void draw() {
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, frameBuffer.getFboId());
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        super.draw();
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
//        changeModel();
        bindData();
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, frameBuffer.getTextureId());
        GLES20.glUniform1i(((TextureShader)shader).getUniTextureLocation(), 0);
        GLES20.glDrawArrays(getDrawGLMode(), 0, vertexs.length / 3);
    }
}
