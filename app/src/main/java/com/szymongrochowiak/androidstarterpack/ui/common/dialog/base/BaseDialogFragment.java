package com.szymongrochowiak.androidstarterpack.ui.common.dialog.base;

import com.szymongrochowiak.androidstarterpack.ui.common.mvp.MvpDialogFragment;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.MvpPresenter;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.MvpView;

/**
 * @author Szymon Grochowiak
 */
public abstract class BaseDialogFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpDialogFragment<V, P> {
}
