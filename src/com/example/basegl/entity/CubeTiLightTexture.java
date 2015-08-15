package com.example.basegl.entity;

import android.opengl.GLES20;

public class CubeTiLightTexture extends CubeLightTexture{
    @Override
    public float[] getVertex() {
        // TODO Auto-generated method stub
        return new float[] {
                // Front face
            -1.0f, 1.0f, 1.0f,              
            -1.0f, -1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 
            -1.0f, -1.0f, 1.0f,                 
            1.0f, -1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            
            // Right face
            1.0f, 1.0f, 1.0f,               
            1.0f, -1.0f, 1.0f,
            1.0f, 1.0f, -1.0f,
            1.0f, -1.0f, 1.0f,              
            1.0f, -1.0f, -1.0f,
            1.0f, 1.0f, -1.0f,
            
            // Back face
            1.0f, 1.0f, -1.0f,              
            1.0f, -1.0f, -1.0f,
            -1.0f, 1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,             
            -1.0f, -1.0f, -1.0f,
            -1.0f, 1.0f, -1.0f,
            
            // Left face
            -1.0f, 1.0f, -1.0f,             
            -1.0f, -1.0f, -1.0f,
            -1.0f, 1.0f, 1.0f, 
            -1.0f, -1.0f, -1.0f,                
            -1.0f, -1.0f, 1.0f, 
            -1.0f, 1.0f, 1.0f, 
            
            // Top face
            -1.0f, 1.0f, -1.0f,             
            -1.0f, 1.0f, 1.0f, 
            1.0f, 1.0f, -1.0f, 
            -1.0f, 1.0f, 1.0f,              
            1.0f, 1.0f, 1.0f, 
            1.0f, 1.0f, -1.0f,
            
            // Bottom face
            1.0f, -1.0f, -1.0f,             
            1.0f, -1.0f, 1.0f, 
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, 1.0f,              
            -1.0f, -1.0f, 1.0f,
            -1.0f, -1.0f, -1.0f,
        };
    }
    @Override
    public float[] getNormals() {
        // TODO Auto-generated method stub
        return new float[] {
            // Front face
            0.0f, 0.0f, 1.0f,               
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,               
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            
            // Right face 
            1.0f, 0.0f, 0.0f,               
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,               
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            
            // Back face 
            0.0f, 0.0f, -1.0f,              
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,              
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            
            // Left face 
            -1.0f, 0.0f, 0.0f,              
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,              
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            
            // Top face 
            0.0f, 1.0f, 0.0f,           
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,               
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            
            // Bottom face 
            0.0f, -1.0f, 0.0f,          
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,              
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f
        };
    }
    @Override
    public float[] getTextures() {
        // TODO Auto-generated method stub
        return new float[] {
            // Front face
            0.0f, 0.0f,                 
            0.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f,             
            
            // Right face 
            0.0f, 0.0f,                 
            0.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f, 
            
            // Back face 
            0.0f, 0.0f,                 
            0.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f, 
            
            // Left face 
            0.0f, 0.0f,                 
            0.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f, 
            
            // Top face 
            0.0f, 0.0f,                 
            0.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f, 
            
            // Bottom face 
            0.0f, 0.0f,                 
            0.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
        };
    }
    @Override
    public int getDrawGLMode() {
        // TODO Auto-generated method stub
        return GLES20.GL_TRIANGLES;
    }
}
