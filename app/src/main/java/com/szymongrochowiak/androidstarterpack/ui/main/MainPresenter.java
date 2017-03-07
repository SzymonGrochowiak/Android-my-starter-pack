package com.szymongrochowiak.androidstarterpack.ui.main;

import com.szymongrochowiak.androidstarterpack.data.network.ApiRepository;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.BasePresenter;

import java.util.Random;

import rx.Subscription;

/**
 * @author Szymon Grochowiak
 */
public class MainPresenter extends BasePresenter<MainView> {

    private ApiRepository mApiRepository;

    public MainPresenter(ApiRepository apiRepository) {
        mApiRepository = apiRepository;
    }

    public void fetchBerry() {
        Subscription fetchBerrySubscription = mApiRepository.getBerry(getBerryId())
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
