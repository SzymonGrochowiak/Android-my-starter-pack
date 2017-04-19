package com.szymongrochowiak.androidstarterpack.ui.common.activities.base;

import android.os.Bundle;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.szymongrochowiak.androidstarterpack.StarterPackApplication;
import com.szymongrochowiak.androidstarterpack.dagger.ApplicationComponent;

/**
 * @author Szymon Grochowiak
 */
public abstract class BaseActivity<V extends MvpView, P extends MvpBasePresenter<V>> extends MvpActivity<V, P> {

    protected abstract void injectDependencies();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectDependencies();
        super.onCreate(savedInstanceState);
    }

    protected ApplicationComponent getDaggerApplicationComponent() {
        return ((StarterPackApplication) getApplication()).getDaggerApplicationComponent();
    }
}
