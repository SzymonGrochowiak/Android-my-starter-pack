package com.szymongrochowiak.androidstarterpack.test.runner;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Szymon Grochowiak
 */
public class AnimationsSwitch {

    private final static String SET_ANIMATION_SCALE_PERMISSION = "android.permission.SET_ANIMATION_SCALE";
    private final static String GRANT_PERMISSION_COMMAND = "pm grant %s %s";

    private final static float ANIMATION_ENABLED = 1.0f;
    private final static float ANIMATION_DISABLED = 0.0f;

    private Method mSetAnimationScalesMethod;
    private Method mGetAnimationScalesMethod;
    private Object mWindowManagerObject;

    public AnimationsSwitch() {
        reflectAnimationMethods();
    }

    public void turnOnAnimations() throws Exception {
        try {
            grantSetAnimationScalePermission();
        } finally {
            setAnimationScaleFactors(ANIMATION_ENABLED);
        }
    }

    public void turnOffAnimations() throws Exception {
        try {
            grantSetAnimationScalePermission();
        } finally {
            setAnimationScaleFactors(ANIMATION_DISABLED);
        }
    }

    private void setAnimationScaleFactors(float scaleFactor) throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        int permStatus = context.checkCallingOrSelfPermission(SET_ANIMATION_SCALE_PERMISSION);
        if (permStatus == PackageManager.PERMISSION_GRANTED) {
            float[] scaleFactors = (float[]) mGetAnimationScalesMethod.invoke(mWindowManagerObject);
            Arrays.fill(scaleFactors, scaleFactor);
            mSetAnimationScalesMethod.invoke(mWindowManagerObject, new Object[]{scaleFactors});
        } else {
            throw new RuntimeException("Cannot grant permission " + SET_ANIMATION_SCALE_PERMISSION);
        }
    }

    private void grantSetAnimationScalePermission() throws IOException {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        String packageName = instrumentation.getTargetContext().getPackageName();
        String command = String.format(GRANT_PERMISSION_COMMAND, packageName, SET_ANIMATION_SCALE_PERMISSION);
        UiDevice.getInstance(instrumentation).executeShellCommand(command);
    }

    private void reflectAnimationMethods() {
        try {
            Class<?> windowManagerStubClazz = Class.forName("android.view.IWindowManager$Stub");
            Method asInterface = windowManagerStubClazz.getDeclaredMethod("asInterface", IBinder.class);
            Class<?> serviceManagerClazz = Class.forName("android.os.ServiceManager");
            Method getService = serviceManagerClazz.getDeclaredMethod("getService", String.class);
            Class<?> windowManagerClazz = Class.forName("android.view.IWindowManager");
            mSetAnimationScalesMethod = windowManagerClazz.getDeclaredMethod("setAnimationScales", float[].class);
            mGetAnimationScalesMethod = windowManagerClazz.getDeclaredMethod("getAnimationScales");
            IBinder windowManagerBinder = (IBinder) getService.invoke(null, "window");
            mWindowManagerObject = asInterface.invoke(null, windowManagerBinder);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access animation methods", e);
        }
    }
}