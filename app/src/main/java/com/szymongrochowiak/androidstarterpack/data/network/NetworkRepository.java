package com.szymongrochowiak.androidstarterpack.data.network;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.local.LocalRepository;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;
import com.szymongrochowiak.androidstarterpack.data.network.ApiInterface;

import java.io.IOException;

import io.realm.RealmObject;
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
                .compose(applySchedulers());
    }

    @Override
    public void start() {
        // Nothing to start
    }

    @NonNull
    private <T extends RealmObject> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).onErrorResumeNext(throwable -> {
                    if (throwable instanceof IOException) {
                        return Observable.empty();
                    }
                    return Observable.error(throwable);
                }).map(object -> {
                    T realmObject = mLocalRepository.saveToRepository(object);
                    return realmObject == null ? object : realmObject;
                });
    }

    @Override
    public void destroy() {
        // Nothing to destroy
    }
}
