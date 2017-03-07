package com.szymongrochowiak.androidstarterpack.data.local;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.Repository;
import com.szymongrochowiak.androidstarterpack.data.model.Berry;

import rx.Observable;

/**
 * @author Szymon Grochowiak
 */

public class LocalRepository implements Repository {

    @NonNull
    @Override
    public Observable<Berry> getBerry(int id) {
        return null;
    }
}
