package com.szymongrochowiak.androidstarterpack.dagger;

import com.szymongrochowiak.androidstarterpack.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Szymon Grochowiak
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkingModule.class, LocalDataModule.class, DataModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
