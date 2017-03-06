package com.szymongrochowiak.androidstarterpack.ui.common.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author Szymon Grochowiak
 */
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends RxAppCompatActivity {

    private P mPresenter;

    @NonNull
    protected abstract P providePresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = providePresenter();
        mPresenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    @NonNull
    public P getPresenter() {
        return mPresenter;
    }
}
