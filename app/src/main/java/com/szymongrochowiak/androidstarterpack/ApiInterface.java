package com.szymongrochowiak.androidstarterpack;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Szymon Grochowiak
 */
public interface ApiInterface {

    @GET("pokemon/{id}")
    Observable<String> getPokemon(@Path("id") int id);
}
