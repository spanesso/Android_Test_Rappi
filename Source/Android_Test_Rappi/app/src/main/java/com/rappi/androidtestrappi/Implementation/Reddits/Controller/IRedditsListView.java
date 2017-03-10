package com.rappi.androidtestrappi.Implementation.Reddits.Controller;

import com.rappi.androidtestrappi.Implementation.Models.Error;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;


import java.util.List;

/**
 * Created by sapanesso on 16/01/17.
 */

public interface IRedditsListView {
    public void success(List<RedditItem> mRedditList);
    public void fail(Error error);
        public void successItem(RedditItem redditItem);
    public void failItem(Error error);

}
