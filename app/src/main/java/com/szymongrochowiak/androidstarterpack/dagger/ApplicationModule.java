package com.szymongrochowiak.androidstarterpack.dagger;

import android.app.Application;

import com.szymongrochowiak.androidstarterpack.data.Repository;

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
