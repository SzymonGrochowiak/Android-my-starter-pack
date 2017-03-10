package com.szymongrochowiak.androidstarterpack.data.local;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;

import io.realm.Realm;
import io.realm.RealmModel;
import rx.Observable;

/**
 * @author Szymon Grochowiak
 */
public class LocalRepository implements Repository {

    @NonNull
    private Realm mRealm;

    public LocalRepository(@NonNull Realm realm) {
        mRealm = realm;
    }

    @NonNull
    @Override
    public Observable<Berry> queryBerry(int id) {
        Berry berry = mRealm.where(Berry.class).equalTo("id", id).findFirst();
        return berry == null ? Observable.empty() : berry.asObservable();
    }

    public <E extends RealmModel> E saveToRepository(@NonNull E object) {
        mRealm.beginTransaction();
        E outputObject = mRealm.copyToRealmOrUpdate(object);
        mRealm.commitTransaction();
        return outputObject;
    }

    @Override
    public void destroy() {
        mRealm.close();
    }
}
