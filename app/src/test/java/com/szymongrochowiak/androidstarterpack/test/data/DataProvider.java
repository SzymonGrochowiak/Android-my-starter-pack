package com.szymongrochowiak.androidstarterpack.test.data;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;
import com.szymongrochowiak.androidstarterpack.test.utils.LocalFileParser;

import io.reactivex.Observable;

/**
 * @author Szymon Grochowiak
 */

public class DataProvider {

    @NonNull
    private final LocalFileParser mAndroidLocalFileParser;
    @NonNull
    private final Gson mGson;

    public DataProvider() {
        mAndroidLocalFileParser = new LocalFileParser();
        mGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    @NonNull
    public Observable<Berry> createBerry() {
        return Observable.just(mGson.fromJson(mAndroidLocalFileParser.readJsonFile("berry.json"), Berry.class));
    }
}
