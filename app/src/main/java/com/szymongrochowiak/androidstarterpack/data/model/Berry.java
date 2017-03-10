package com.szymongrochowiak.androidstarterpack.data.model;

import android.support.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Szymon Grochowiak
 */
public class Berry extends RealmObject {

    @PrimaryKey
    int id;
    @NonNull
    String name;

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Berry berry = (Berry) o;

        if (id != berry.id) return false;
        return name.equals(berry.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
