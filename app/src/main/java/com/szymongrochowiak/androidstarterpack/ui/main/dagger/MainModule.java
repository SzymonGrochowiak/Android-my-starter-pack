package com.szymongrochowiak.androidstarterpack.ui.main.dagger;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.RepositoryLifecycle;
import com.szymongrochowiak.androidstarterpack.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Szymon Grochowiak
 */
@Module
public class MainModule {

    @Provides
    @MainScope
    protected MainPresenter providePresenter(Repository repository, RepositoryLifecycle repositoryLifecycle) {
        return new MainPresenter(repository, repositoryLifecycle);
    }
}
