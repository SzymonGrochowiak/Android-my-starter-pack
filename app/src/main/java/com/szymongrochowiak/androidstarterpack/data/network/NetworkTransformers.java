package com.szymongrochowiak.androidstarterpack.data.network;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.RepositoryWriter;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Szymon Grochowiak
 */
class NetworkTransformers {

    private NetworkTransformers() {
    }

    @NonNull
    public static <T> ObservableTransformer<T, T> applyTransformations(
            List<ObservableTransformer<T, T>> transformers) {
        return observable -> {
            for (ObservableTransformer<T, T> transformer : transformers) {
                observable = observable.compose(transformer);
            }
            return observable;
        };
    }

    @NonNull
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    public static <T> ObservableTransformer<T, T> applyOnErrorResumeNext() {
        return observable -> observable.onErrorResumeNext(throwable -> {
            if (throwable instanceof IOException) {
                return Observable.empty();
            }
            return Observable.error(throwable);
        });
    }

    @NonNull
    public static <T> ObservableTransformer<T, T> applySaveLocally(RepositoryWriter repositoryWriter) {
        return observable -> observable.map(object -> {
            T savedObject = repositoryWriter.saveToRepository(object);
            return savedObject == null ? object : savedObject;
        });
    }
}
