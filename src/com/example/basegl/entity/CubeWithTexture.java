package com.example.basegl.entity;

import java.nio.FloatBuffer;

import android.graphics.Bitmap;
import android.opengl.GLES20;

import com.tikdik.opengl.shader.BaseShader;
import com.tikdik.opengl.shader.TextureShader;
import com.tikdik.opengl.utils.BufferUtils;
import com.tikdik.opengl.utils.TextureHelper;
import com.tikdik.res.ResHelper;

public class CubeWithTexture extends CubePoint{
    //1.vertex data
    float textures[];
    public float[] getTextures() {
        return new float[] {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
        };
    }
    FloatBuffer textureBuffer;
    int textureId;
    public CubeWithTexture() {
        super();
        textures = getTextures();
        textureBuffer = BufferUtils.allocateDirectFloatBuffer(textures.length);
        textureBuffer.put(textures);
        textureBuffer.position(0);
    }
    @Override
    BaseShader createShader() {
        return new TextureShader();
    }
    @Override
    public void initGLEnv() {
        super.initGLEnv();
        Bitmap bitmap = ResHelper.loadBitmapFromAssetFile("scenery.jpg");
        textureId = TextureHelper.loadTexture(bitmap);
        bitmap.recycle();
        if (textureId == 0) {
            throw new RuntimeException("load picture failed!");
        }
    }
    /*
     * use texture
     * 1.active texture
     * 2.bind texture
     * 3.set to shader
     * @see com.example.basegl.entity.CubePoint#bindData()
     */
    @Override
    public void bindData() {
        super.bindData();
        textureBuffer.position(0);
        GLES20.glVertexAttribPointer(((TextureShader)shader).getAttrTexturePositionLocation(),
                2, GLES20.GL_FLOAT, false, 0, textureBuffer);
        GLES20.glEnableVertexAttribArray(((TextureShader)shader).getAttrTexturePositionLocation());
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
        GLES20.glUniform1i(((TextureShader)shader).getUniTextureLocation(), 0);
        
    }
    @Override
    public int getDrawGLMode() {
        return GLES20.GL_TRIANGLE_STRIP;
    }
}
