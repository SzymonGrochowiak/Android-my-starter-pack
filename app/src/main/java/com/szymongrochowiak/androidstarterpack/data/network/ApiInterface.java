package com.szymongrochowiak.androidstarterpack.data.network;

import com.szymongrochowiak.androidstarterpack.data.model.Berry;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Szymon Grochowiak
 */
public interface ApiInterface {

    @GET("berry/{id}")
    Observable<Berry> getBerry(@Path("id") int id);
}