package com.szymongrochowiak.androidstarterpack.ui.common.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * @author Szymon Grochowiak
 */

public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends RxFragment {

    private P mPresenter;

    @NonNull
    protected abstract P providePresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = providePresenter();
        mPresenter.attach((V) this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detach();
    }

    @NonNull
    public P getPresenter() {
        return mPresenter;
    }
}
