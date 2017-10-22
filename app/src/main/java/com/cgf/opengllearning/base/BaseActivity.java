package com.cgf.opengllearning.base;

import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

/**
 * @author zhenghanrong on 2017/10/22.
 */

public class BaseActivity extends AppCompatActivity {
    protected boolean checkSupported() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ConfigurationInfo info = manager.getDeviceConfigurationInfo();
        boolean supportsEs2 = info.reqGlEsVersion >= 0x2000;
        boolean isEmulator = Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                && (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86"));
        return supportsEs2 || isEmulator;
    }
}
