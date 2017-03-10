package com.szymongrochowiak.androidstarterpack.dagger;

import com.szymongrochowiak.androidstarterpack.data.local.LocalRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * @author Szymon Grochowiak
 */
@Module
public class LocalDataModule {

    @Provides
    @Singleton
    LocalRepository provideLocalRepository() {
        return new LocalRepository();
    }
}
