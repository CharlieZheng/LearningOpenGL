package com.example.practice3;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyGLSurfaceView view = new MyGLSurfaceView(this);
      /*  view.setEGLContextClientVersion(2);
        view.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        view.setRenderer(new EffectsRenderer(this));*/
        setContentView(view);
    }
}
