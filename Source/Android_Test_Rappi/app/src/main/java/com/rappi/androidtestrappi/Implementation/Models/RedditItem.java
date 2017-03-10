package com.rappi.androidtestrappi.Implementation.Models;


import java.util.HashMap;


import io.realm.RealmList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sebastiangomez on 24/02/16.
 */
public class RedditItem extends RealmObject {

    @PrimaryKey
    private String id;
    private RealmList<RedditDataItem> redditDataItemList;

    public RealmList<RedditDataItem> getRedditDataItemList() {
        return redditDataItemList;
    }

    public void setRedditDataItemList(RealmList<RedditDataItem> redditDataItemList) {
        this.redditDataItemList = redditDataItemList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
