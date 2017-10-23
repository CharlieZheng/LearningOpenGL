package com.cgf.opengllearning.renderer

import android.opengl.GLSurfaceView
import java.nio.ShortBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * @author zhenghanrong on 2017/10/23.
 */
class VortexRenderer : GLSurfaceView.Renderer {
    private var _red = 0.9f
    private var _green = 0.2f
    private var _blue = 0.2f

    private var _indexBuffer: ShortBuffer?=null
    override fun onDrawFrame(gl: GL10?) {
        gl?.glClearColor(_red, _green, _blue, 1f)
        // 为了让颜色变化可见，我们必须调用glClear()以及颜色缓冲的Mask来清空buffer，然后为我们的底色使用新的底色。
        gl?.glClear(GL10.GL_COLOR_BUFFER_BIT)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        gl?.glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
    }

    fun setColor(r: Float, g: Float, b: Float) {
        _red = r
        _green = g
        _blue = b
    }


}