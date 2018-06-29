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
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final String U_COLOR = "u_Color";
    private static final String A_POSITION = "a_Position";
    private static final int BYTES_PER_FLOAT = 4;

    private final FloatBuffer vertexData;
    private final Context context;
    private int uColorLocation;
    private int aPositionLocation;
    private int program;
    private float[] tableVerticesWithTriangles = {
            // Triangle 1
            -0.5f, -0.5f,
            0.5f, 0.5f,
            -0.5f, 0.5f,
            // Triangle 2
            -0.5f, -0.5f,
            0.5f, -0.5f,
            0.5f, 0.5f,
            // Line 1
            -0.5f, 0f,
            0.5f, 0f,
            // Mallets
            0f, -0.25f,
            0f, 0.25f
    };
    public AirHockeyRenderer(Context context) {
        this.context = context;
        float[] tableVertices = {
                0f, 0f,
                0f, 14f,
                9f, 14f,
                9f, 0f
        };
        vertexData = ByteBuffer.allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(tableVerticesWithTriangles);
    }


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(0f, 0f, 0f, 0f); // rgba
        String vertexShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_vertex_shader);
        String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader);
        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
        int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);
        program = ShaderHelper.linkProgram(vertexShader, fragmentShader);
        if (LoggerConfig.ON) {
            ShaderHelper.validateProgram(program);
        }
        GLES20.glUseProgram(program);
        uColorLocation = GLES20.glGetUniformLocation(program, U_COLOR);
        aPositionLocation = GLES20.glGetAttribLocation(program, A_POSITION);
        vertexData.position(0);
        GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, 0, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        // 这会擦除屏幕上的所有颜色，并用之前glClearColor()调用定义的颜色填充整个屏幕。
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glUniform4f(uColorLocation, 1f, 1f, 1f, 1f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, // 三角形
                0, 6 // 6个顶点
        );
        GLES20.glUniform4f(uColorLocation, 1f, 0f, 0f, 1f);
        GLES20.glDrawArrays(GLES20.GL_LINES, 6, // 从tableVerticesWithTriangles数组的第6个元素读入2个顶点
                2);
        // Draw the first mallet blue.
        GLES20.glUniform4f(uColorLocation, 0f, 0f, 1f, 1f);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 8, 1);
        // Draw the second mallet blue.
        GLES20.glUniform4f(uColorLocation, 1f, 0f, 0f, 1f);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 9, 1);
    }
}
