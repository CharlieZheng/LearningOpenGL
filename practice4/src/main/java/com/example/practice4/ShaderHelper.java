package com.example.practice4;

import android.opengl.GLES20;
import android.util.Log;

public class ShaderHelper {
    private static final String TAG = ShaderHelper.class.getSimpleName();

    private static int compileShader(int type, String shaderCode) {
     final int shaderObjectId = GLES20.glCreateShader(type);
     if (shaderObjectId ==0) {
         if (LoggerConfig.ON) {
             Log.w(TAG, "Could not create new shader.");
         }
         return 0;
     }
     GLES20.glShaderSource(shaderObjectId, shaderCode);
     GLES20.glCompileShader(shaderObjectId);
     final int[] compileStatus = new int[1];
     GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus ,0);
     if (LoggerConfig.ON) {
         Log.v(TAG, "Results of compiling source: " + "\n" + shaderCode+"\n: "+GLES20.glGetShaderInfoLog(shaderObjectId));
     }
    return -1;
    }
    // 片段着色器
    public static int compileFragmentShader (String shaderCode) {
        return compileShader(GLES20.GL_FRAGMENT_SHADER, shaderCode);
    }
    // 顶点着色器
    public static int compileVertexShader(String shaderCode)
    {
        return compileShader(GLES20.GL_VERTEX_SHADER, shaderCode);
    }
}
