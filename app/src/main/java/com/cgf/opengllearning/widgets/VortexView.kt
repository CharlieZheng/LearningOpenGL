package com.cgf.opengllearning.widgets

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.MotionEvent
import com.cgf.opengllearning.renderer.VortexRenderer

/**
 * @author zhenghanrong on 2017/10/23.
 */
class VortexView : GLSurfaceView {
    private var _x = 0f
    private var _y = 0f
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
        if (event?.action == MotionEvent.ACTION_DOWN) {
            _x = event?.x?:0f
            _y = event?.y?:0f
        }
        if (event?.action == MotionEvent.ACTION_MOVE) {
            val xdiff :Float= _x - (event?.x?:0f)
            val ydiff :Float= _y - (event?.y?:0f)
            queueEvent({
                _renderer?._xAngle = (_renderer?._xAngle?:0f) +ydiff
                _renderer?._yAngle = (_renderer?._yAngle?:0f) + xdiff
            })
            _x = event?.x?:0f
            _y = event?.y?:0f
        }
        return true
    }
}