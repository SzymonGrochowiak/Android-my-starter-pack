package com.szymongrochowiak.androidstarterpack.data;

import android.support.annotation.NonNull;

import com.szymongrochowiak.androidstarterpack.data.model.Berry;

import io.reactivex.Observable;

/**
 * @author Szymon Grochowiak
 */
public interface Repository {

    @NonNull
    Observable<Berry> queryBerry(int id);
}
