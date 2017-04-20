package com.szymongrochowiak.androidstarterpack.ui.common.mvp;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author Szymon Grochowiak
 */
public class BasePresenter<V extends MvpView> extends MvpBasePresenter<V> {

    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @NonNull
    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            mCompositeDisposable.dispose();
        }
    }
}