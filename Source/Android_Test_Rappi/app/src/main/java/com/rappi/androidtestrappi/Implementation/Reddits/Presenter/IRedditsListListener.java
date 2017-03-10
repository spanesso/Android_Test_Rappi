package com.rappi.androidtestrappi.Implementation.Reddits.Presenter;

import com.rappi.androidtestrappi.Implementation.Models.Error;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;

import java.util.List;

/**
 * Created by sapanesso on 16/01/17.
 */

public interface IRedditsListListener {
    public void getListSuccess(List<RedditItem> mRedditList);
    public void getItemSuccess(RedditItem redditItem);
    public void getItemFaliure(Error error);
    public void getListFaliure(Error error);

}
