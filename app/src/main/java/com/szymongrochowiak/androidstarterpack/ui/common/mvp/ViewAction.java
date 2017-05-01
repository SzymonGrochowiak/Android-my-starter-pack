package com.szymongrochowiak.androidstarterpack.ui.common.mvp;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * @author Szymon Grochowiak
 */

public interface ViewAction<V extends MvpView> {
    void perform(V view);
}
