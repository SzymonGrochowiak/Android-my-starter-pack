package com.szymongrochowiak.androidstarterpack.data;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.model.Berry;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

/**
 * @author Szymon Grochowiak
 */
public class ApplicationRepository implements Repository {

    @NonNull
    private final List<Repository> mRepositoryList;

    public ApplicationRepository(Repository... repositories) {
        mRepositoryList = Arrays.asList(repositories);
        if (mRepositoryList.isEmpty()) {
            throw new IllegalStateException("It has to be at least one repository.");
        }
    }

    @NonNull
    @Override
    public Observable<Berry> queryBerry(int id) {
        return Observable.from(mRepositoryList).concatMap(
                repository -> repository.queryBerry(id));
    }

    @Override
    public void destroy() {
        Observable.from(mRepositoryList).forEach(Repository::destroy);
    }
}
