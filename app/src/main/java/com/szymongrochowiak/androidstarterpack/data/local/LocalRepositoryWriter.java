package com.szymongrochowiak.androidstarterpack.data.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.szymongrochowiak.androidstarterpack.data.RepositoryWriter;

import io.realm.Realm;
import io.realm.RealmModel;

/**
 * @author Szymon Grochowiak
 */
class LocalRepositoryWriter implements RepositoryWriter {

    @Nullable
    private Realm mRealm;

    @Nullable
    @Override
    public <T> T saveToRepository(@NonNull T object) {
        T outputObject;
        try {
            RealmModel realmObject = (RealmModel) object;
            getRealm().beginTransaction();
            outputObject = (T) mRealm.copyToRealmOrUpdate(realmObject);
            mRealm.commitTransaction();
        } catch (ClassCastException e) {
            throw new ClassCastException("Saving object should extend RealmObject");
        }
        return outputObject;
    }

    @NonNull
    protected Realm getRealm() {
        if (mRealm == null) {
            throw new NullPointerException("Realm not initialized. Use start to initialize.");
        }
        return mRealm;
    }

    protected void setRealm(@NonNull Realm realm) {
        mRealm = realm;
    }
}
