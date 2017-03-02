package com.szymongrochowiak.androidstarterpack.ui.common.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author Szymon Grochowiak
 */

public abstract class MvpActivity<V extends MvpView, P extends Presenter<V>> extends RxAppCompatActivity implements
        MvpView<P> {

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = providePresenter();
        mPresenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dettach();
    }

    @NonNull
    public P getPresenter() {
        return mPresenter;
    }
}
