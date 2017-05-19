package com.szymongrochowiak.androidstarterpack.test.utils;

import android.app.Instrumentation;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Szymon Grochowiak
 */
public class DisableAnimationsRule implements TestRule {

    private final static float ANIMATION_ENABLED = 1.0f;
    private final static float ANIMATION_DISABLED = 0.0f;

    private Method mSetAnimationScalesMethod;
    private Method mGetAnimationScalesMethod;
    private Object mWindowManagerObject;

    public DisableAnimationsRule() {
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

    @Override
    public Statement apply(final Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                grantSetAnimationScalePermission();
                setAnimationScaleFactors(ANIMATION_DISABLED);
                try {
                    statement.evaluate();
                } finally {
                    setAnimationScaleFactors(ANIMATION_ENABLED);
                }
            }
        };
    }

    private void setAnimationScaleFactors(float scaleFactor) throws Exception {
        float[] scaleFactors = (float[]) mGetAnimationScalesMethod.invoke(mWindowManagerObject);
        Arrays.fill(scaleFactors, scaleFactor);
        mSetAnimationScalesMethod.invoke(mWindowManagerObject, new Object[]{scaleFactors});
    }

    private void grantSetAnimationScalePermission() throws IOException {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        String packageName = instrumentation.getTargetContext().getPackageName();
        UiDevice.getInstance(instrumentation)
                .executeShellCommand("pm grant " + packageName + " android.permission.SET_ANIMATION_SCALE");
    }
}