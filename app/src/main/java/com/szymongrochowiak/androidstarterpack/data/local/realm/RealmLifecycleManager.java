package com.szymongrochowiak.androidstarterpack.data.local.realm;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.szymongrochowiak.androidstarterpack.data.RepositoryLifecycle;

import io.realm.Realm;

/**
 * @author Szymon Grochowiak
 */
public class RealmLifecycleManager implements RepositoryLifecycle {

    @Nullable
    private Realm mRealm;

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

    private boolean isRealmAvailable() {
        return mRealm != null && !mRealm.isClosed();
    }

    @NonNull
    public Realm getRealm() {
        if (mRealm == null) {
            throw new NullPointerException("Realm not initialized. Use start to initialize.");
        }
        return mRealm;
    }
}
