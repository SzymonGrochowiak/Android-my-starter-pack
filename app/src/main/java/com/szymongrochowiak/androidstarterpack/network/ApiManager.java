package com.szymongrochowiak.androidstarterpack.network;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.model.Berry;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Szymon Grochowiak
 */
public class ApiManager {

    private ApiInterface mApiInterface;

    public ApiManager(@NonNull ApiInterface apiInterface) {
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
