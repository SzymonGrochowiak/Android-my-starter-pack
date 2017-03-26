package com.szymongrochowiak.androidstarterpack.data.local;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.RepositoryLifecycle;
import com.szymongrochowiak.androidstarterpack.data.local.realm.RealmLifecycleManager;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;

import io.realm.Realm;
import rx.Observable;

/**
 * @author Szymon Grochowiak
 */
public class LocalRepository implements Repository, RepositoryLifecycle {

    @NonNull
    private final RealmLifecycleManager mRealmLifecycleManager;
    @NonNull
    private final LocalRepositoryWriter mLocalRepositoryWriter;

    public LocalRepository() {
        mRealmLifecycleManager = new RealmLifecycleManager();
        mLocalRepositoryWriter = new LocalRepositoryWriter();
    }

    @Override
    public void start() {
        mRealmLifecycleManager.start();
        mLocalRepositoryWriter.setRealm(mRealmLifecycleManager.getRealm());
    }

    @Override
    public void destroy() {
        mRealmLifecycleManager.destroy();
    }

    @NonNull
    private Realm getRealm() {
        return mRealmLifecycleManager.getRealm();
    }

    @NonNull
    public LocalRepositoryWriter getLocalRepositoryWriter() {
        return mLocalRepositoryWriter;
    }

    @NonNull
    @Override
    public Observable<Berry> queryBerry(int id) {
        Berry berry = getRealm().where(Berry.class).equalTo("id", id).findFirst();
        return berry == null ? Observable.empty() : berry.asObservable();
    }
}
