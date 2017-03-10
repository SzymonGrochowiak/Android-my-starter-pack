package com.szymongrochowiak.androidstarterpack.data.local;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;

import io.realm.Realm;
import rx.Observable;

/**
 * @author Szymon Grochowiak
 */
public class LocalRepository implements Repository {

    private Realm mRealm;

    public LocalRepository(Realm realm) {
        mRealm = realm;
    }

    @NonNull
    @Override
    public Observable<Berry> queryBerry(int id) {
        return null;
    }

    @Override
    public void destroy() {
        mRealm.close();
    }
}
