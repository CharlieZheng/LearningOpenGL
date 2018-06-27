 - https://blog.csdn.net/lb377463323/article/details/52136518

1. 在Triangle的初始化函数中
```
FloatBuffer: ByteBuffer, triangleCoords: float[]
    this = ByteBuffer.asFloatBuffer()
    this.put(triangleCoords)
    this.position(0)
ByteBuffer:
    this = ByteBuffer.allocateDirect()
    this.order(ByteOrder.nativeOrder())
triangleCoords:
    this = [0.0f,  1.0f, 0.0f,
           -1.0f, -0.0f, 0.0f,
            1.0f, -0.0f, 0.0f]
```

# Practice3

 - https://blog.csdn.net/smbroe/article/details/46311997

# Practice4

 - http://oje4qwxal.bkt.clouddn.com/android/OpenGL%20ES%E5%BA%94%E7%94%A8%E5%BC%80%E5%8F%91%E5%AE%9E%E8%B7%B5%E6%8C%87%E5%8D%97%20%20Android%E5%8D%B7.pdf