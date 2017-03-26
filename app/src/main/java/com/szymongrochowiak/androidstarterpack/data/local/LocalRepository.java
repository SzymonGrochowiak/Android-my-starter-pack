package com.szymongrochowiak.androidstarterpack.data.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.RepositoryLifecycle;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;

import io.realm.Realm;
import io.realm.RealmModel;
import rx.Observable;

/**
 * @author Szymon Grochowiak
 */
public class LocalRepository implements Repository, RepositoryLifecycle {

    @Nullable
    private Realm mRealm;

    public LocalRepository() {
    }

    @Override
    public void start() {
        if (!isRealmAvailable()) {
            mRealm = Realm.getDefaultInstance();
        }
    }

    @Override
    public void destroy() {
        if (mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
    }

    @NonNull
    @Override
    public Observable<Berry> queryBerry(int id) {
        throwExceptionIfRealmNotAvailable();
        Berry berry = mRealm.where(Berry.class).equalTo("id", id).findFirst();
        return berry == null ? Observable.empty() : berry.asObservable();
    }

    @Nullable
    public <E extends RealmModel> E saveToRepository(@NonNull E object) {
        throwExceptionIfRealmNotAvailable();
        mRealm.beginTransaction();
        E outputObject = mRealm.copyToRealmOrUpdate(object);
        mRealm.commitTransaction();
        return outputObject;
    }

    private void throwExceptionIfRealmNotAvailable() {
        if (!isRealmAvailable()) {
            throw new NullPointerException("Repository was not started");
        }
    }

    private boolean isRealmAvailable() {
        return mRealm != null && !mRealm.isClosed();
    }
}
