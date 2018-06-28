package com.example.practice4;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class AirHockeyRenderer implements GLSurfaceView.Renderer {
    private  static  final  int POSITION_COMPONENT_COUNT = 2;
    float[] tableVerticesWithTriangles = {
            // Triangle 1
            0f, 0f,
            9f, 14f,
            0f, 14f,
            // Triangle 2
            0f, 0f,
            9f, 0f,
            9f, 14f,
            // Line 1
            0f, 7f,
            9f, 7f,
            // Mallets
            4.5f, 2f,
            4.5f, 12f
    };
    public AirHockeyRenderer(Context context) {
        this.context = context;
        float[] tableVertices = {
          0f,0f,
          0f,14f,
          9f, 14f,
          9f,0f
        };
        vertexData = ByteBuffer.allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(tableVerticesWithTriangles);
    }
    private  static  final  int BYTES_PER_FLOAT = 4;
    private  final FloatBuffer vertexData;
    private final Context context;
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(1f, 0f, 0f, 0f); // rgba
        String vertexShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader);
        String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0,width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        // 这会擦除屏幕上的所有颜色，并用之前glClearColor()调用定义的颜色填充整个屏幕。
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }
}
