package com.example.practice2;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context){
        super(context);

        // 创建OpenGL ES 2.0的上下文
        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer();

        //设置Renderer用于绘图
        setRenderer(mRenderer);

        //只有在绘制数据改变时才绘制view，可以防止GLSurfaceView帧重绘
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // 这是Android中的基础知识，这里就不讲解了

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                // 反向旋转至中线以上
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // 反向旋转至中线左面
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }

                mRenderer.setAngle(
                        mRenderer.getAngle() +
                                ((dx + dy) * TOUCH_SCALE_FACTOR));
                requestRender();//请求渲染
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
}