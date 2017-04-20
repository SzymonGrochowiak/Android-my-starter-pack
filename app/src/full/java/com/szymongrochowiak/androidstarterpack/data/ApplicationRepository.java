package com.szymongrochowiak.androidstarterpack.data;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.model.Berry;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author Szymon Grochowiak
 */
public class ApplicationRepository implements Repository, RepositoryLifecycle {

    @NonNull
    private final List<Repository> mRepositoryList;

    public ApplicationRepository(Repository... repositoriesByPriority) {
        mRepositoryList = Arrays.asList(repositoriesByPriority);
        if (mRepositoryList.isEmpty()) {
            throw new IllegalStateException("It has to be at least one repository.");
        }
    }

    @Override
    public void start() {
        Observable.fromIterable(mRepositoryList).forEach(repository -> {
            if (repository instanceof RepositoryLifecycle) {
                ((RepositoryLifecycle) repository).start();
            }
        });
    }

    @Override
    public void destroy() {
        Observable.fromIterable(mRepositoryList).forEach(repository -> {
            if (repository instanceof RepositoryLifecycle) {
                ((RepositoryLifecycle) repository).destroy();
            }
        });
    }

    @NonNull
    @Override
    public Observable<Berry> queryBerry(int id) {
        return Observable.fromIterable(mRepositoryList)
                .concatMap(repository -> repository.queryBerry(id))
                .filter(berry -> berry != null);
    }
}
