package com.cgf.opengllearning.activities;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cgf.opengllearning.R;
import com.cgf.opengllearning.base.BaseActivity;
import com.cgf.opengllearning.renderer.MyFirstRenderer;

public class MainActivity extends BaseActivity {
    private GLSurfaceView glSurfaceView;

    private TextView errTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();

        if (checkSupported()) {
            initGLSurfaceView();
        } else {
            errTextView.setText("该设备不支持OpenGL");
        }
    }

    private void initWidgets() {



        errTextView = (TextView) findViewById(R.id.err_text_view);
    }

    private void initGLSurfaceView() {
        glSurfaceView = (GLSurfaceView) findViewById(R.id.gl_surface_view);
        glSurfaceView.setRenderer(new MyFirstRenderer());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (glSurfaceView != null) glSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        if (glSurfaceView != null) glSurfaceView.onPause();
        super.onPause();
    }


}
