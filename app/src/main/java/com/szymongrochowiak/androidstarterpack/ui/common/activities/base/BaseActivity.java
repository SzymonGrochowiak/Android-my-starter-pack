package com.szymongrochowiak.androidstarterpack.ui.common.activities.base;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * @author Szymon Grochowiak
 */
public abstract class BaseActivity<V extends MvpView, P extends MvpBasePresenter<V>> extends MvpActivity<V, P> {
}
