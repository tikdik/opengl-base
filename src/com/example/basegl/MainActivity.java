package com.example.basegl;


import com.example.basegl.entity.CubePoint;
import com.tikdik.context.GlobalContext;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends Activity {
    GLSurfaceView glContainerView;
    Renderer glRender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalContext.gContext = getApplicationContext();
        glContainerView = new MyGLView(this);
        glContainerView.setEGLContextClientVersion(2);
        glRender = new CubeRender();
        String name = getIntent().getStringExtra("class_name");
        try {
            CubePoint cubeShape = (CubePoint) Class.forName(name).newInstance();
            ((CubeRender)glRender).setCubeShape(cubeShape);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        glContainerView.setRenderer(glRender);
        setContentView(glContainerView);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            break;
        default:
            break;
        }
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        glContainerView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        glContainerView.onPause();
    }
    class MyGLView extends GLSurfaceView {
        public MyGLView(Context context) {
            super(context);
        }
    }
}
