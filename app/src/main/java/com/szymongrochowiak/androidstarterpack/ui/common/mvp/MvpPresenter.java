package com.szymongrochowiak.androidstarterpack.ui.common.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Szymon Grochowiak
 */
public class MvpPresenter<V extends MvpView> {

    @Nullable
    private V mMvpView;

    void attach(@NonNull V mvpView) {
        if (isAttached()) {
            throw new IllegalStateException("Presenter is already attached.");
        }
        mMvpView = mvpView;
    }

    void detach() {
        mMvpView = null;
    }

    public boolean isAttached() {
        return mMvpView != null;
    }

    @NonNull
    public V getMvpView() {
        if (mMvpView == null) {
            throw new NullPointerException("No view attached to presenter.");
        }
        return mMvpView;
    }
}
