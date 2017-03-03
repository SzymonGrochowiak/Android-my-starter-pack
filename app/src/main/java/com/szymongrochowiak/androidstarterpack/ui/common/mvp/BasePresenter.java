package com.szymongrochowiak.androidstarterpack.ui.common.mvp;

import rx.subscriptions.CompositeSubscription;

/**
 * @author Szymon Grochowiak
 */

public class BasePresenter<V extends MvpView> extends MvpPresenter<V> {

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public CompositeSubscription getCompositeSubscription() {
        return mCompositeSubscription;
    }

    @Override
    void detach() {
        mCompositeSubscription.unsubscribe();
        super.detach();
    }
}