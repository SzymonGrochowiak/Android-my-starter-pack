package com.szymongrochowiak.androidstarterpack.ui.common.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Szymon Grochowiak
 */

public class Presenter<V extends MvpView> {

    @Nullable
    private V mMvpView;

    void attach(@NonNull V mvpView) {
        mMvpView = mvpView;
    }

    void dettach() {
        mMvpView = null;
    }

    boolean isAttached() {
        return mMvpView != null;
    }
}
