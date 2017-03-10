package com.rappi.androidtestrappi.Implementation.Models;


import java.util.HashMap;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sebastiangomez on 24/02/16.
 */
public class RedditDataItem extends RealmObject {

    @PrimaryKey
    private String id;
    private String key;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
