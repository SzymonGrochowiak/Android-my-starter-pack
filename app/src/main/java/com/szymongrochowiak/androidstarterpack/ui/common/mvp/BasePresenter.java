package com.szymongrochowiak.androidstarterpack.ui.common.mvp;

import android.support.annotation.NonNull;

import rx.subscriptions.CompositeSubscription;

/**
 * @author Szymon Grochowiak
 */
public class BasePresenter<V extends MvpView> extends MvpPresenter<V> {

    private CompositeSubscription mCompositeSubscription;

    public CompositeSubscription getCompositeSubscription() {
        return mCompositeSubscription;
    }

    @Override
    void attach(@NonNull V mvpView) {
        super.attach(mvpView);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    void detach() {
        mCompositeSubscription.unsubscribe();
        super.detach();
    }
}