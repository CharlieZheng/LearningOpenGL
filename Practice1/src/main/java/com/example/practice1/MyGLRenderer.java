package com.example.practice1;


import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {
    private Triangle mTriangle;

    public static int loadShader(int type, String shaderCode) {

        //创建一个vertex shader类型(GLES20.GL_VERTEX_SHADER)
        //或一个fragment shader类型(GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // 将源码添加到shader并编译它
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        //设置背景色（r,g,b,a）
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);//白色不透明
        //初始化三角形
        mTriangle = new Triangle();
    }

    public void onDrawFrame(GL10 unused) {
//重绘背景色
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        mTriangle.draw();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
//绘制窗口
        GLES20.glViewport(0, 0, width, height);
    }

}