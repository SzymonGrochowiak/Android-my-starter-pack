package com.szymongrochowiak.androidstarterpack.data.network;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.RepositoryWriter;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Szymon Grochowiak
 */
class NetworkTransformers {

    private NetworkTransformers() {
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> applyTransformations(
            List<Observable.Transformer<T, T>> transformers) {
        return observable -> {
            for (Observable.Transformer<T, T> transformer : transformers) {
                observable = observable.compose(transformer);
            }
            return observable;
        };
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> applyOnErrorResumeNext() {
        return observable -> observable.onErrorResumeNext(throwable -> {
            if (throwable instanceof IOException) {
                return Observable.empty();
            }
            return Observable.error(throwable);
        });
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> applySaveLocally(RepositoryWriter repositoryWriter) {
        return observable -> observable.map(object -> {
            T savedObject = repositoryWriter.saveToRepository(object);
            return savedObject == null ? object : savedObject;
        });
    }
}
