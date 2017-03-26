package com.szymongrochowiak.androidstarterpack.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Szymon Grochowiak
 */
public interface RepositoryWriter {

    @Nullable
    <T> T saveToRepository(@NonNull T object);
}
