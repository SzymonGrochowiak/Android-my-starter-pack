package com.szymongrochowiak.androidstarterpack.ui.common.fragments.base;

import com.szymongrochowiak.androidstarterpack.ui.common.mvp.MvpFragment;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.MvpView;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.Presenter;

/**
 * @author Szymon Grochowiak
 */
public abstract class BaseFragment<V extends MvpView, P extends Presenter<V>> extends MvpFragment<V, P> {
}
