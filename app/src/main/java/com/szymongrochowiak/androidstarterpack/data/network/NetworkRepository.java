package com.szymongrochowiak.androidstarterpack.data.network;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.local.LocalRepository;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;
import com.szymongrochowiak.androidstarterpack.data.network.ApiInterface;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Szymon Grochowiak
 */
public class NetworkRepository implements Repository {

    @NonNull
    private LocalRepository mLocalRepository;
    @NonNull
    private ApiInterface mApiInterface;

    public NetworkRepository(@NonNull LocalRepository localRepository, @NonNull ApiInterface apiInterface) {
        mLocalRepository = localRepository;
        mApiInterface = apiInterface;
    }

    @NonNull
    @Override
    public Observable<Berry> queryBerry(int id) {
        return mApiInterface.getBerry(id)
                .compose(applySchedulers()).map(berry -> {
                    Berry realmBerry = mLocalRepository.saveToRepository(berry);
                    return realmBerry == null ? berry : realmBerry;
                });
    }

    @Override
    public void start() {
        // Nothing to start
    }

    @NonNull
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void destroy() {
        // Nothing to destroy
    }
}
