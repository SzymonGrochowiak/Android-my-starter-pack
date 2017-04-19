package com.szymongrochowiak.androidstarterpack.ui.main.dagger;

import com.szymongrochowiak.androidstarterpack.dagger.ApplicationComponent;
import com.szymongrochowiak.androidstarterpack.ui.main.MainActivity;
import com.szymongrochowiak.androidstarterpack.ui.main.MainPresenter;

import dagger.Component;

/**
 * @author Szymon Grochowiak
 */
@MainScope
@Component(dependencies = ApplicationComponent.class, modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);

    MainPresenter presenter();
}
