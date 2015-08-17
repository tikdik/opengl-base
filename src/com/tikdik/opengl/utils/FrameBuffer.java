package com.tikdik.opengl.utils;

import android.opengl.GLES20;

public class FrameBuffer {
    int fboId = 0;
    int renderBufferId = 0;
    int textureId = 0;
    int weidth, height;
    public int getFboId() {
        return fboId;
    }
    public int getTextureId() {
        return textureId;
    }
    public void unInit() {
        int[] temp = new int[1];
        if (fboId > 0) {
            temp[0] = fboId;
            GLES20.glDeleteBuffers(1, temp, 0);
        }
        if (renderBufferId > 0) {
            temp[0] = renderBufferId;
            GLES20.glDeleteRenderbuffers(1, temp, 0);
        }
        if (textureId > 0) {
            temp[0] = textureId;
            GLES20.glDeleteTextures(1, temp, 0);
        }
    }
    public void init(int weidth, int height) {
        this.weidth = weidth;
        this.height = height;
        unInit();
        int[] temp = new int[1];
        //generate render buffer for frame depth buffer
        //bind render buffer
        //create render buffer storage
        GLES20.glGenRenderbuffers(1, temp, 0);
        renderBufferId = temp[0];
        GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, renderBufferId);
        GLHelper.checkGlError("glBindRenderbuffer");
        GLES20.glRenderbufferStorage(GLES20.GL_RENDERBUFFER, GLES20.GL_DEPTH_COMPONENT16, weidth, height);
        //generate texture for frame color buffer
        GLES20.glGenTextures(1, temp, 0);
        textureId = temp[0];
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
        GLHelper.checkGlError("glBindTexture");
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, weidth, height, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        //generate fbo id
        //bind fbo
        //bind color and depth buffer
        GLES20.glGenFramebuffers(1, temp, 0);
        fboId = temp[0];
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, fboId);
        GLHelper.checkGlError("glBindFramebuffer");
        GLES20.glFramebufferRenderbuffer(GLES20.GL_FRAMEBUFFER, GLES20.GL_DEPTH_ATTACHMENT, GLES20.GL_RENDERBUFFER, renderBufferId);
        GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, textureId, 0);
        //restor to default buffer
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, 0);
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
        GLHelper.checkGlError("glBindFramebuffer");
    }
}
