package com.szymongrochowiak.androidstarterpack.data;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.szymongrochowiak.androidstarterpack.StarterPackApplication;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;
import com.szymongrochowiak.androidstarterpack.utils.AssetsFileParser;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Szymon Grochowiak
 */
public class ApplicationRepository implements Repository, RepositoryLifecycle {

    // For tests issues this has to be false
    public static final boolean SIMULATE_CONNECTION_DELAY = false;
    private static final int INTERNET_CONNECTION_DELAY_SECONDS = 3;

    @NonNull
    private AssetsFileParser mAndroidAssetsFileParser;

    public ApplicationRepository(Repository... repositoriesByPriority) {
        mAndroidAssetsFileParser = new AssetsFileParser(StarterPackApplication.getInstance());
    }

    @Override
    public void start() {
        // do nothing
    }

    @Override
    public void destroy() {
        // do nothing
    }

    @NonNull
    @Override
    public Observable<Berry> queryBerry(int id) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        try {
            Berry berry = gson.fromJson(mAndroidAssetsFileParser.readJsonFile("berry.json"), Berry.class);
            if (SIMULATE_CONNECTION_DELAY) {
                return Observable.timer(INTERNET_CONNECTION_DELAY_SECONDS, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(aLong -> Observable.just(berry));
            }
            return Observable.just(berry);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
