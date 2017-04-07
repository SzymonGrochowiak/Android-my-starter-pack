package com.szymongrochowiak.androidstarterpack.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.szymongrochowiak.androidstarterpack.data.ApplicationRepository;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.BasePresenter;

import java.util.Random;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author Szymon Grochowiak
 */
public class MainPresenter extends BasePresenter<MainView> {

    @NonNull
    private ApplicationRepository mRepository;

    @Nullable
    private Berry mBerry;
    @Nullable
    private String mErrorMessage;

    public MainPresenter(@NonNull ApplicationRepository repository) {
        mRepository = repository;
    }

    public void queryBerry() {
        if (restoreViewStateIfExist()) {
            return;
        }
        if (isViewAttached()) {
            getView().showLoading();
        }
        Subscription fetchBerrySubscription = mRepository.queryBerry(getBerryId()).observeOn(AndroidSchedulers
                .mainThread())
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
                });
        getCompositeSubscription().add(fetchBerrySubscription);
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
        mRepository.start();
    }

    public void destroyRepository() {
        mRepository.destroy();
    }
}
