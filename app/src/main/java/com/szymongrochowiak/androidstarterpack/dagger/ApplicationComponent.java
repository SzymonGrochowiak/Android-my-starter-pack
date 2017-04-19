package com.szymongrochowiak.androidstarterpack.dagger;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.RepositoryLifecycle;
import com.szymongrochowiak.androidstarterpack.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Szymon Grochowiak
 */
@Singleton
@Component(modules = {ApplicationModule.class, LocalDataModule.class, NetworkingModule.class, RepositoryModule.class})
public interface ApplicationComponent {

    Repository repository();

    RepositoryLifecycle repositoryLifecycle();
}