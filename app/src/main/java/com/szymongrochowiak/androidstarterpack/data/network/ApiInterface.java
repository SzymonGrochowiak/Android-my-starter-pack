package com.szymongrochowiak.androidstarterpack.data.network;

import com.szymongrochowiak.androidstarterpack.data.model.Berry;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Szymon Grochowiak
 */
public interface ApiInterface {

    @GET("berry/{id}")
    Observable<Berry> getBerry(@Path("id") int id);
}
