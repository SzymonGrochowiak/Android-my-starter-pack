package com.szymongrochowiak.androidstarterpack.ui.common.activities.base;

import com.szymongrochowiak.androidstarterpack.ui.common.mvp.MvpActivity;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.MvpView;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.Presenter;

/**
 * @author Szymon Grochowiak
 */
public abstract class BaseActivity<V extends MvpView, P extends Presenter<V>> extends MvpActivity<V, P> {
}
