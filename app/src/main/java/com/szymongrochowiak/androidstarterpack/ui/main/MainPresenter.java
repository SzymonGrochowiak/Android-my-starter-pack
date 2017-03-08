package com.szymongrochowiak.androidstarterpack.ui.main;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.ApplicationRepository;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.BasePresenter;

import java.util.Random;

import rx.Subscription;

/**
 * @author Szymon Grochowiak
 */
public class MainPresenter extends BasePresenter<MainView> {

    @NonNull
    private ApplicationRepository mRepository;

    public MainPresenter(@NonNull ApplicationRepository repository) {
        mRepository = repository;
    }

    public void fetchBerry() {
        Subscription fetchBerrySubscription = mRepository.getBerry(getBerryId())
                .subscribe(berry -> {
                    if (isAttached()) {
                        getMvpView().showBerryName(berry.getName());
                    }
                }, throwable -> {
                    if (isAttached()) {
                        getMvpView().showBerryFetchError(throwable.toString());
                    }
                });
        getCompositeSubscription().add(fetchBerrySubscription);
    }

    private int getBerryId() {
        return new Random().nextInt(30);
    }
}
