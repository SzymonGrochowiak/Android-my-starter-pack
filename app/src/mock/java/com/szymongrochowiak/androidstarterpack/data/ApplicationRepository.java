package com.szymongrochowiak.androidstarterpack.data;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;

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

    public ApplicationRepository(Repository... repositoriesByPriority) {
        // do nothing
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
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Berry berry = gson.fromJson("{\"natural_gift_type\":{\"url\":\"http:\\/\\/pokeapi" +
                        ".co\\/api\\/v2\\/type\\/2\\/\",\"name\":\"fighting\"},\"name\":\"mock leppa\"," +
                        "\"max_harvest\":5,\"soil_dryness\":15,\"smoothness\":20," +
                        "\"item\":{\"url\":\"http:\\/\\/pokeapi.co\\/api\\/v2\\/item\\/131\\/\"," +
                        "\"name\":\"leppa-berry\"},\"firmness\":{\"url\":\"http:\\/\\/pokeapi" +
                        ".co\\/api\\/v2\\/berry-firmness\\/4\\/\",\"name\":\"very-hard\"},\"growth_time\":4,\"id\":6," +
                        "\"size\":28}",
                Berry.class);
        if (SIMULATE_CONNECTION_DELAY) {
            return Observable.timer(INTERNET_CONNECTION_DELAY_SECONDS, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(aLong -> Observable.just(berry));
        }
        return Observable.just(berry);
    }
}
