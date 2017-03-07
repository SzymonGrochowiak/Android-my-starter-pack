package com.szymongrochowiak.androidstarterpack.data.network;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;
import com.szymongrochowiak.androidstarterpack.data.network.ApiInterface;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Szymon Grochowiak
 */
public class ApiRepository implements Repository {

    private ApiInterface mApiInterface;

    public ApiRepository(@NonNull ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }

    public Observable<Berry> getBerry(int id) {
        return mApiInterface.getBerry(id)
                .compose(applySchedulers());
    }

    private <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
