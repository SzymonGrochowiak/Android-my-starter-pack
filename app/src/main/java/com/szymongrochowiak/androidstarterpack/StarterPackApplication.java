package com.szymongrochowiak.androidstarterpack;

import android.app.Application;

import com.szymongrochowiak.androidstarterpack.dagger.ApplicationComponent;
import com.szymongrochowiak.androidstarterpack.dagger.DaggerApplicationComponent;
import com.szymongrochowiak.androidstarterpack.dagger.LocalDataModule;
import com.szymongrochowiak.androidstarterpack.dagger.NetworkingModule;
import com.szymongrochowiak.androidstarterpack.dagger.RepositoryModule;

import io.reactivex.plugins.RxJavaPlugins;
import io.realm.Realm;
import timber.log.Timber;

/**
 * @author Szymon Grochowiak
 */
public class StarterPackApplication extends Application {

    public static final String BASE_ENDPOINT = "http://pokeapi.co/api/v2/";

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
                .localDataModule(new LocalDataModule())
                .networkingModule(new NetworkingModule(getApiEndpoint()))
                .repositoryModule(new RepositoryModule())
                .build();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initRxJava() {
        RxJavaPlugins.setErrorHandler(throwable -> Timber.e(throwable, "RxError"));
    }

    public String getApiEndpoint() {
        return BASE_ENDPOINT;
    }

    public ApplicationComponent getDaggerApplicationComponent() {
        return mDaggerComponent;
    }
}
