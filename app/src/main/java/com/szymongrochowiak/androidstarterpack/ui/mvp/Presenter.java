package com.szymongrochowiak.androidstarterpack.ui.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Szymon Grochowiak
 */

public class Presenter {

    @Nullable
    private MvpView mMvpView;

    void attach(@NonNull MvpView mvpView) {
        mMvpView = mvpView;
    }

    void dettach() {
        mMvpView = null;
    }

    boolean isAttached() {
        return mMvpView != null;
    }
}
