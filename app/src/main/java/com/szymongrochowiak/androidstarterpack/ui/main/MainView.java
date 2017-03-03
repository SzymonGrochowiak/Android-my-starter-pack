package com.szymongrochowiak.androidstarterpack.ui.main;

import com.szymongrochowiak.androidstarterpack.ui.common.mvp.MvpView;

/**
 * @author Szymon Grochowiak
 */

public interface MainView extends MvpView {

    void showBerryName(String berryName);

    void showBerryFetchError(String errorMessage);
}
