package com.szymongrochowiak.androidstarterpack.data.network;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.RepositoryWriter;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

import static com.szymongrochowiak.androidstarterpack.data.network.NetworkTransformers.applyOnErrorResumeNext;
import static com.szymongrochowiak.androidstarterpack.data.network.NetworkTransformers.applySaveLocally;
import static com.szymongrochowiak.androidstarterpack.data.network.NetworkTransformers.applySchedulers;
import static com.szymongrochowiak.androidstarterpack.data.network.NetworkTransformers.applyTransformations;

/**
 * @author Szymon Grochowiak
 */
public class NetworkRepository implements Repository {

    @NonNull
    private RepositoryWriter mRepositoryWriter;
    @NonNull
    private ApiInterface mApiInterface;

    public NetworkRepository(@NonNull RepositoryWriter repositoryWriter, @NonNull ApiInterface apiInterface) {
        mRepositoryWriter = repositoryWriter;
        mApiInterface = apiInterface;
    }

    private <T> ObservableTransformer<T, T> applyRequestTransformations() {
        List<ObservableTransformer<T, T>> transformerList =
                Arrays.asList(applySchedulers(), applyOnErrorResumeNext(), applySaveLocally(mRepositoryWriter));
        return applyTransformations(transformerList);
    }

    @NonNull
    @Override
    public Observable<Berry> queryBerry(int id) {
        return mApiInterface.getBerry(id).compose(applyRequestTransformations());
    }
}
