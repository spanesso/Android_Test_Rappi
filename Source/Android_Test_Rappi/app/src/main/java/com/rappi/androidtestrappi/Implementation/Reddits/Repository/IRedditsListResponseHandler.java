package com.rappi.androidtestrappi.Implementation.Reddits.Repository;

import com.rappi.androidtestrappi.Implementation.Models.Error;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;


import java.util.List;

/**
 * Created by sapanesso on 16/01/17.
 */

public interface IRedditsListResponseHandler {
    public void onAPIResponse(Error error, List<RedditItem> mRedditList);
    public void onAPIResponseItem(Error error, RedditItem redditItem);
}
