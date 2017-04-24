package com.szymongrochowiak.androidstarterpack.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.RepositoryLifecycle;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.BasePresenter;

import java.util.Random;

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

    public void queryBerry() {
        if (restoreViewStateIfExist()) {
            return;
        }
        if (isViewAttached()) {
            getView().showLoading();
        }
        Disposable fetchBerryDisposable = mRepository.queryBerry(getBerryId())
                .subscribe(berry -> {
                    mBerry = berry;
                    if (isViewAttached()) {
                        getView().showContent(mBerry);
                    }
                }, throwable -> {
                    mErrorMessage = throwable.toString();
                    if (isViewAttached()) {
                        getView().showError(mErrorMessage);
                    }
                }, () -> {
                    if (isViewAttached()) {
                        getView().hideLoading();
                    }
                });
        getCompositeDisposable().add(fetchBerryDisposable);
    }

    private boolean restoreViewStateIfExist() {
        if (isViewAttached()) {
            if (mBerry != null) {
                getView().showContent(mBerry);
                return true;
            }
            if (mErrorMessage != null) {
                getView().showError(mErrorMessage);
                return true;
            }
        }
        return false;
    }

    private int getBerryId() {
        return new Random().nextInt(20);
    }

    public void startRepository() {
        mRepositoryLifecycle.start();
    }

    public void destroyRepository() {
        mRepositoryLifecycle.destroy();
    }
}
