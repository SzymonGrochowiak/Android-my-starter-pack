package com.szymongrochowiak.androidstarterpack.ui.common.activities.base;

import com.szymongrochowiak.androidstarterpack.ui.common.mvp.MvpActivity;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.MvpView;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.MvpPresenter;

/**
 * @author Szymon Grochowiak
 */
public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> {
}
