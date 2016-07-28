package com.szymongrochowiak.androidstarterpack;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Szymon Grochowiak
 */
@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesStarterPackApplication() {
        return mApplication;
    }
}
