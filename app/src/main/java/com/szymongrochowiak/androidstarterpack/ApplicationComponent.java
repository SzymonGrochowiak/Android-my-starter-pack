package com.szymongrochowiak.androidstarterpack;

import com.szymongrochowiak.androidstarterpack.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Szymon Grochowiak
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
