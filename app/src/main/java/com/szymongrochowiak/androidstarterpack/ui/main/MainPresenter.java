package com.szymongrochowiak.androidstarterpack.ui.main;

import com.szymongrochowiak.androidstarterpack.network.ApiManager;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.BasePresenter;

import java.util.Random;

import rx.Subscription;

/**
 * @author Szymon Grochowiak
 */

public class MainPresenter extends BasePresenter<MainView> {

    private ApiManager mApiManager;

    public MainPresenter(ApiManager apiManager) {
        mApiManager = apiManager;
    }

    public void fetchBerry() {
        Subscription fetchBerrySubscription = mApiManager.getBerry(getBerryId())
                .subscribe(berry -> {
                    if (isAttached()) {
                        getMvpView().showBerryName(berry.getName());
                    }
                }, throwable -> {
                    if (isAttached()) {
                        getMvpView().showBerryName(throwable.toString());
                    }
                });
        getCompositeSubscription().add(fetchBerrySubscription);
    }

    private int getBerryId() {
        return new Random().nextInt(30);
    }
}
