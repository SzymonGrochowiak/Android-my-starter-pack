package com.szymongrochowiak.androidstarterpack.test;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.test.espresso.Espresso;
import android.support.test.runner.AndroidJUnitRunner;

import com.szymongrochowiak.androidstarterpack.TestApplication;
import com.szymongrochowiak.androidstarterpack.test.utils.RxIdlingResource;

import io.appflate.restmock.RESTMockServerStarter;
import io.appflate.restmock.android.AndroidAssetsFileParser;
import io.appflate.restmock.android.AndroidLogger;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * @author Szymon Grochowiak
 */

public class CustomTestRunner extends AndroidJUnitRunner {

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
