package com.szymongrochowiak.androidstarterpack;

import android.app.Application;

import com.szymongrochowiak.androidstarterpack.dagger.ApplicationComponent;
import com.szymongrochowiak.androidstarterpack.dagger.DaggerApplicationComponent;

import io.realm.Realm;
import rx.plugins.RxJavaHooks;
import timber.log.Timber;

/**
 * @author Szymon Grochowiak
 */
public class StarterPackApplication extends Application {

    private ApplicationComponent mDaggerComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        initDaggerComponent();
        initTimber();
        initRxJava();
    }

    private void initRealm() {
        Realm.init(this);
    }

    private void initDaggerComponent() {
        mDaggerComponent = DaggerApplicationComponent.builder()
                .build();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initRxJava() {
        RxJavaHooks.setOnError(throwable -> Timber.e(throwable, "RxError"));
    }

    public ApplicationComponent getDaggerApplicationComponent() {
        return mDaggerComponent;
    }
}
