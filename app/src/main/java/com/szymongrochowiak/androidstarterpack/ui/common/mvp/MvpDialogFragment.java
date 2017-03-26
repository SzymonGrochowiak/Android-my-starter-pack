package com.szymongrochowiak.androidstarterpack.ui.common.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxDialogFragment;

/**
 * @author Szymon Grochowiak
 */
public abstract class MvpDialogFragment<V extends MvpView, P extends MvpPresenter<V>> extends RxDialogFragment {

    @Nullable
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
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    @NonNull
    public P getPresenter() {
        if (mPresenter == null) {
            throw new NullPointerException("Presenter not attached");
        }
        return mPresenter;
    }
}
