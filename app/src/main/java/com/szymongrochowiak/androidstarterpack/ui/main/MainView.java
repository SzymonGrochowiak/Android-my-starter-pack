package com.szymongrochowiak.androidstarterpack.ui.main;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;

/**
 * @author Szymon Grochowiak
 */
public interface MainView extends MvpView {

    void showLoading();

    void hideLoading();

    void showContent(@NonNull Berry berry);

    void showError(@NonNull String errorMessage);
}
