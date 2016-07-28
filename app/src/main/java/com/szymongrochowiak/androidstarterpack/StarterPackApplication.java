package com.szymongrochowiak.androidstarterpack;

import android.app.Application;

/**
 * @author Szymon Grochowiak
 */
public class StarterPackApplication extends Application {

    private ApplicationComponent mDaggerComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDaggerComponent();
    }

    private void initDaggerComponent() {
        mDaggerComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getDaggerApplicationComponent() {
        return mDaggerComponent;
    }
}
