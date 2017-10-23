package com.cgf.opengllearning.renderer

import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
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

    private var _indexBuffer: ShortBuffer? = null
    private var _vertexBuffer: FloatBuffer? = null
    private var _colorBuffer:FloatBuffer?=null
    private val _indicesArray: ShortArray = shortArrayOf(0, 1, 2)
    private val _nrOfVertices = 3 // 三个顶点
    private var _angle: Float = 0f
    override fun onDrawFrame(gl: GL10?) {
        gl?.glClearColor(_red, _green, _blue, 1f)
        gl?.glLoadIdentity() // 重置矩阵，这会重设三角形的角度以便其总是可以旋转到给定的角度。
        // 为了让颜色变化可见，我们必须调用glClear()以及颜色缓冲的Mask来清空buffer，然后为我们的底色使用新的底色。
        gl?.glClear(GL10.GL_COLOR_BUFFER_BIT)
        gl?.glRotatef(_angle, 0f, 1f, 0f)
        gl?.glColor4f(0.5f, 0f, 0f, 0.5f) // 设置三角形为暗红色
        // 初始化Vertex Pointer
        // 第一个参数是大小，也是顶点的维数。我们使用的是x,y,z三维坐标。第二个参数，GL_FLOAT定义buffer中使用的数据类型。第三个变量是0，是因为我们的坐标是在数组中紧凑的排列的，没有使用offset。最后哦胡第四个参数顶点缓冲。
        gl?.glVertexPointer(3, GL10.GL_FLOAT, 0, _vertexBuffer)
        // 第一个参数定义了什么样的图元将被画出来。第二个参数定义有多少个元素，第三个是indices使用的数据类型。最后一个是绘制顶点使用的索引缓冲。
        gl?.glDrawElements(GL10.GL_TRIANGLES, _nrOfVertices, GL10.GL_UNSIGNED_SHORT, _indexBuffer)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        gl?.glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        // 设置OpenGL使用vertex数组来画。这是很重要的，因为如果不这么设置OpenGL不知道如何处理我们的数据。
        gl?.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        initTriangle()
    }

    fun setColor(r: Float, g: Float, b: Float) {
        _red = r
        _green = g
        _blue = b
    }

    fun setAngle(angle: Float) {
        _angle = angle
    }

    private fun initTriangle() {
        val vbb = ByteBuffer.allocateDirect(_nrOfVertices * 3 * 4) // 分配内存
        vbb.order(ByteOrder.nativeOrder())
        _vertexBuffer = vbb.asFloatBuffer()

        val ibb = ByteBuffer.allocateDirect(_nrOfVertices * 2)
        ibb.order(ByteOrder.nativeOrder())
        _indexBuffer = ibb.asShortBuffer()

        val coords = floatArrayOf(-0.5f, -0.5f, 0f, // (x1, y1, z1)
                0.5f, -0.5f, 0f, // (x2, y2, z2)
                0f, 0.5f, 0f) // (x3, y3, z3)

        _vertexBuffer?.put(coords) // 保存坐标
        _indexBuffer?.put(_indicesArray) // 保存索引
        _vertexBuffer?.position(0)
        _indexBuffer?.position(0)
    }
}