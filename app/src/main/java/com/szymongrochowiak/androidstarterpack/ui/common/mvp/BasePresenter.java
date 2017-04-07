package com.szymongrochowiak.androidstarterpack.ui.common.mvp;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import rx.subscriptions.CompositeSubscription;

/**
 * @author Szymon Grochowiak
 */
public class BasePresenter<V extends MvpView> extends MvpBasePresenter<V> {

    @NonNull
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @NonNull
    public CompositeSubscription getCompositeSubscription() {
        return mCompositeSubscription;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            mCompositeSubscription.unsubscribe();
        }
    }
}