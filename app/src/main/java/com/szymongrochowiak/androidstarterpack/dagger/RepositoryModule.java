package com.szymongrochowiak.androidstarterpack.dagger;

import com.szymongrochowiak.androidstarterpack.data.ApplicationRepository;
import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.RepositoryLifecycle;
import com.szymongrochowiak.androidstarterpack.data.local.LocalRepository;
import com.szymongrochowiak.androidstarterpack.data.network.NetworkRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Szymon Grochowiak
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    ApplicationRepository provideApplicationRepository(NetworkRepository networkRepository, LocalRepository
            localRepository) {
        return new ApplicationRepository(networkRepository, localRepository);
    }

    @Provides
    @Singleton
    Repository provideRepository(ApplicationRepository applicationRepository) {
        return applicationRepository;
    }

    @Provides
    @Singleton
    RepositoryLifecycle provideRepositoryLifecycle(ApplicationRepository applicationRepository) {
        return applicationRepository;
    }
}
