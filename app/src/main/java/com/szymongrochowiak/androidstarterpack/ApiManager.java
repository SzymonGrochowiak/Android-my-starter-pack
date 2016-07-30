package com.szymongrochowiak.androidstarterpack;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Scheduler;
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

    public Observable<String> getPokemon(int id) {
        return mApiInterface.getPokemon(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
