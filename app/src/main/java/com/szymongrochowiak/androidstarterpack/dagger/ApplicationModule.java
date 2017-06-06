package com.szymongrochowiak.androidstarterpack.dagger;

import android.app.Application;

import com.szymongrochowiak.androidstarterpack.StarterPackApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Szymon Grochowiak
 */
@Module
public class ApplicationModule {

    private StarterPackApplication mApplication;

    public ApplicationModule(StarterPackApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    protected StarterPackApplication providesStarterPackApplication() {
        return mApplication;
    }
}
