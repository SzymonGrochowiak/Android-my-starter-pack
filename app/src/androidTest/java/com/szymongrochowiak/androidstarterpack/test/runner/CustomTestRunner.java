package com.szymongrochowiak.androidstarterpack.test.runner;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.test.espresso.Espresso;
import android.support.test.runner.AndroidJUnitRunner;
import android.util.Log;

import com.szymongrochowiak.androidstarterpack.TestApplication;

import io.appflate.restmock.RESTMockServerStarter;
import io.appflate.restmock.android.AndroidAssetsFileParser;
import io.appflate.restmock.android.AndroidLogger;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * @author Szymon Grochowiak
 */
public class CustomTestRunner extends AndroidJUnitRunner {

    private static final String TAG = CustomTestRunner.class.getSimpleName();

    private AnimationsSwitch mAnimationsSwitch = new AnimationsSwitch();

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestApplication.class.getName(), context);
    }

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        startMockServer();
        initRxIdlingResource();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onStart() {
        try {
            mAnimationsSwitch.turnOffAnimations();
            Log.i(TAG, "Animation switch off");
        } catch (Exception e) {
            throw new RuntimeException("Animation switch exception", e);
        }
        // This has to be called at the end
        super.onStart();
    }

    @Override
    public void finish(int resultCode, Bundle results) {
        try {
            mAnimationsSwitch.turnOnAnimations();
            Log.i(TAG, "Animation switch on");
        } catch (Exception e) {
            throw new RuntimeException("Animation switch exception", e);
        }
        // This has to be called at the end
        super.finish(resultCode, results);
    }

    private void startMockServer() {
        RESTMockServerStarter.startSync(new AndroidAssetsFileParser(getContext()), new AndroidLogger());
    }

    private void initRxIdlingResource() {
        RxIdlingResource rxIdlingResource = new RxIdlingResource();
        Espresso.registerIdlingResources(rxIdlingResource);
        RxJavaPlugins.setScheduleHandler(rxIdlingResource);
    }
}