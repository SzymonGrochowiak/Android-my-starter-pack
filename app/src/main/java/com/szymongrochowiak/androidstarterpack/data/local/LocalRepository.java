package com.szymongrochowiak.androidstarterpack.data.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;

import io.realm.Realm;
import io.realm.RealmModel;
import rx.Observable;

/**
 * @author Szymon Grochowiak
 */
public class LocalRepository implements Repository {

    @Nullable
    private Realm mRealm;

    public LocalRepository() {
    }

    @NonNull
    @Override
    public Observable<Berry> queryBerry(int id) {
        if (!isRealmAvailable()) {
            return Observable.empty();
        }
        Berry berry = mRealm.where(Berry.class).equalTo("id", id).findFirst();
        return berry == null ? Observable.empty() : berry.asObservable();
    }

    @Override
    public void start() {
        if (!isRealmAvailable()) {
            mRealm = Realm.getDefaultInstance();
        }
    }

    @Nullable
    public <E extends RealmModel> E saveToRepository(@NonNull E object) {
        if (!isRealmAvailable()) {
            return null;
        }
        mRealm.beginTransaction();
        E outputObject = mRealm.copyToRealmOrUpdate(object);
        mRealm.commitTransaction();
        return outputObject;
    }

    @Override
    public void destroy() {
        if (mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
    }

    private boolean isRealmAvailable() {
        return mRealm != null && !mRealm.isClosed();
    }
}
