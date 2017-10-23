package com.cgf.opengllearning.widgets

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.MotionEvent
import com.cgf.opengllearning.renderer.VortexRenderer

/**
 * @author zhenghanrong on 2017/10/23.
 */
class VortexView : GLSurfaceView
{
    constructor(context: Context?) : super(context) {
        init(context)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context?) {
        _renderer = VortexRenderer()
        setRenderer(_renderer)
    }
    private var _renderer: VortexRenderer? = null
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        queueEvent({
            _renderer?.setColor(event?.x?:0f / width, event?.y?:0f / height, 1f)
        })
        return true
    }
}