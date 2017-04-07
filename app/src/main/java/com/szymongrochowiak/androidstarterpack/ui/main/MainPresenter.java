package com.szymongrochowiak.androidstarterpack.ui.main;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.ApplicationRepository;
import com.szymongrochowiak.androidstarterpack.ui.common.mvp.BasePresenter;

import java.util.Random;

import rx.Subscription;
import timber.log.Timber;

/**
 * @author Szymon Grochowiak
 */
public class MainPresenter extends BasePresenter<MainView> {

    @NonNull
    private ApplicationRepository mRepository;

    public MainPresenter(@NonNull ApplicationRepository repository) {
        mRepository = repository;
    }

    public void queryBerry() {
        Subscription fetchBerrySubscription = mRepository.queryBerry(getBerryId())
                .subscribe(berry -> {
                    if (isViewAttached()) {
                        getView().showBerryName(berry.getName());
                    }
                }, throwable -> {
                    Timber.e(throwable);
                    if (isViewAttached()) {
                        getView().showBerryFetchError(throwable.toString());
                    }
                });
        getCompositeSubscription().add(fetchBerrySubscription);
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
