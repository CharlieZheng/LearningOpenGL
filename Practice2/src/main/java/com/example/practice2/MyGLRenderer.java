package com.example.practice2;


import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {
    private Triangle mTriangle;
    // mMVPMatrix是"Model View Projection Matrix"的缩写
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];//定义投影矩阵变量
    private final float[] mViewMatrix = new float[16];//定义相机视图矩阵变量

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
    private float[] mRotationMatrix = new float[16];

    public void onDrawFrame(GL10 unused) {
        float[] scratch = new float[16];
//重绘背景色
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        // 设置相机的位置(视图矩阵)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // 将mProjectionMatrix和mViewMatrix矩阵相乘并赋给mMVPMatrix
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);




        // 创建旋转矩阵
//        long time = SystemClock.uptimeMillis() % 4000L;
//        float angle = 0.090f * ((int) time);
        Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0, 0, -1.0f);

        // 将旋转矩阵合并到投影和相机视图变换矩阵中
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);

        // 绘制三角形
        mTriangle.draw(scratch);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
//绘制窗口
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;//GLSurfaceView的宽高比

        // 根据六个面定义投影矩阵  frustumM(float[] m, int offset, float left, float right, float bottom, float top, float near, float far)
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }
    public volatile float mAngle;

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }
}