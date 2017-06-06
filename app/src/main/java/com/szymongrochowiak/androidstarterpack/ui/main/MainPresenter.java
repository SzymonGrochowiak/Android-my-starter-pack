package com.szymongrochowiak.androidstarterpack.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.RepositoryLifecycle;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.BasePresenter;

import io.reactivex.disposables.Disposable;

/**
 * @author Szymon Grochowiak
 */
public class MainPresenter extends BasePresenter<MainView> {

    @NonNull
    private Repository mRepository;
    @NonNull
    private RepositoryLifecycle mRepositoryLifecycle;

    @Nullable
    private Berry mBerry;
    @Nullable
    private String mErrorMessage;

    public MainPresenter(@NonNull Repository repository, @NonNull RepositoryLifecycle repositoryLifecycle) {
        mRepository = repository;
        mRepositoryLifecycle = repositoryLifecycle;
    }

    public void queryBerry(int berryId) {
        if (restoreViewStateIfExist()) {
            return;
        }
        if (isViewAttached()) {
            getView().showLoading();
        }
        Disposable fetchBerryDisposable = mRepository.queryBerry(berryId)
                .subscribe(berry -> {
                    mBerry = berry;
                    sendToView(view -> view.showContent(mBerry));
                }, throwable -> {
                    mErrorMessage = throwable.toString();
                    sendToView(view -> view.showError(mErrorMessage));
                    sendToView(MainView::hideLoading);
                }, () -> sendToView(MainView::hideLoading));
        getCompositeDisposable().add(fetchBerryDisposable);
    }

    private boolean restoreViewStateIfExist() {
        if (mBerry != null) {
            sendToView(view -> view.showContent(mBerry));
            return true;
        }
        if (mErrorMessage != null) {
            sendToView(view -> view.showError(mErrorMessage));
            return true;
        }
        return false;
    }

    public void startRepository() {
        mRepositoryLifecycle.start();
    }

    public void destroyRepository() {
        mRepositoryLifecycle.destroy();
    }
}
