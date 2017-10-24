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

    private var _indexBuffer: ShortBuffer? = null
    private var _vertexBuffer: FloatBuffer? = null
    private var _colorBuffer: FloatBuffer? = null

    private var _nrOfVertices = 0 // 顶点数
    var _xAngle = 0f
    var _yAngle = 0f
    override fun onDrawFrame(gl: GL10?) {
        gl?.glClearColor(0f, 0f, 0f, 1f)
        gl?.glLoadIdentity() // 重置矩阵，这会重设三角形的角度以便其总是可以旋转到给定的角度。
        // 为了让颜色变化可见，我们必须调用glClear()以及颜色缓冲的Mask来清空buffer，然后为我们的底色使用新的底色。
        gl?.glClear(GL10.GL_COLOR_BUFFER_BIT)
        gl?.glRotatef(_xAngle, 1f, 0f, 0f)
        gl?.glRotatef(_yAngle, 0f, 1f, 0f)
        // gl?.glColor4f(0.5f, 0f, 0f, 0.5f) // 设置三角形为暗红色
        // 初始化Vertex Pointer
        // 第一个参数是大小，也是顶点的维数。我们使用的是x,y,z三维坐标。第二个参数，GL_FLOAT定义buffer中使用的数据类型。第三个变量是0，是因为我们的坐标是在数组中紧凑的排列的，没有使用offset。最后哦胡第四个参数顶点缓冲。
        gl?.glVertexPointer(3, GL10.GL_FLOAT, 0, _vertexBuffer)
        gl?.glColorPointer(4, GL10.GL_FLOAT, 0, _colorBuffer)
        // 第一个参数定义了什么样的图元将被画出来。第二个参数定义有多少个元素，第三个是indices使用的数据类型。最后一个是绘制顶点使用的索引缓冲。
        gl?.glDrawElements(GL10.GL_TRIANGLES, _nrOfVertices, GL10.GL_UNSIGNED_SHORT, _indexBuffer)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        gl?.glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        gl?.glEnable(GL10.GL_CULL_FACE) // 只有一面可见
        gl?.glFrontFace(GL10.GL_CCW) // 定义哪种顺序来分正反（背）面
        gl?.glCullFace(GL10.GL_BACK) // 设置不可见的那面为反（背）面
        // 设置OpenGL使用vertex数组来画。这是很重要的，因为如果不这么设置OpenGL不知道如何处理我们的数据。
        gl?.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl?.glEnableClientState(GL10.GL_COLOR_ARRAY)
        initTriangle()
    }

    private fun initTriangle() {
        val coords = floatArrayOf(-0.5f, -0.5f, 0.5f, // (x1, y1, z1)
                0.5f, -0.5f, 0.5f, // (x2, y2, z2)
                0f, -0.5f, -0.5f, // (x3, y3, z3)
                0f, 0.5f, 0f) // (x3, y3, z3)
        _nrOfVertices = coords.size
        val colors = floatArrayOf(1f, 0f, 0f, 1f, // point 1
                0f, 1f, 0f, 1f, // point 2
                0f, 0f, 1f, 1f, // point 3
                1f, 1f, 1f, 1f // point 4
        )
        val indices = shortArrayOf(0, 1, 3,
                0, 2, 1,
                0, 3, 2,
                1, 2, 3)
        // float has 4 bytes, coordinate * 4 bytes
        val vbb = ByteBuffer.allocateDirect(coords.size * 4) // 分配内存
        vbb.order(ByteOrder.nativeOrder())
        _vertexBuffer = vbb.asFloatBuffer()

        // short has 2 bytes, indices * 2 bytes
        val ibb = ByteBuffer.allocateDirect(indices.size * 2)
        ibb.order(ByteOrder.nativeOrder())
        _indexBuffer = ibb.asShortBuffer()
        // float has 4 bytes, colors (RGBA) * 4 bytes
        val cbb = ByteBuffer.allocateDirect(colors.size * 4)
        cbb.order(ByteOrder.nativeOrder())
        _colorBuffer = cbb.asFloatBuffer()


        _vertexBuffer?.put(coords) // 保存坐标
        _indexBuffer?.put(indices) // 保存索引
        _colorBuffer?.put(colors)
        _vertexBuffer?.position(0)
        _indexBuffer?.position(0)
        _colorBuffer?.position(0)
    }
}