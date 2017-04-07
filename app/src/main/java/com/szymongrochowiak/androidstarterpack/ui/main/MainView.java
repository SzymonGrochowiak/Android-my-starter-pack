package com.szymongrochowiak.androidstarterpack.ui.main;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * @author Szymon Grochowiak
 */
public interface MainView extends MvpView {

    void showBerryName(String berryName);

    void showBerryFetchError(String errorMessage);
}
