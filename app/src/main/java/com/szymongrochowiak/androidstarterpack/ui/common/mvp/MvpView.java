package com.szymongrochowiak.androidstarterpack.ui.common.mvp;

import android.support.annotation.NonNull;

/**
 * @author Szymon Grochowiak
 */

public interface MvpView<P extends Presenter> {

    @NonNull
    P providePresenter();
}
