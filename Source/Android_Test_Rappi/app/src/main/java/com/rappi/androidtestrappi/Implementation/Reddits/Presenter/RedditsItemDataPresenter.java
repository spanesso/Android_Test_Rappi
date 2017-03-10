package com.rappi.androidtestrappi.Implementation.Reddits.Presenter;

import com.rappi.androidtestrappi.App.Base.BasePresenter;
import com.rappi.androidtestrappi.Implementation.Models.Error;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;
import com.rappi.androidtestrappi.Implementation.Reddits.BusinessLayer.RedditsListBL;
import com.rappi.androidtestrappi.Implementation.Reddits.Controller.IRedditsListView;

import java.util.List;

/**
 * Created by sapanesso on 16/01/17.
 */

public class RedditsItemDataPresenter extends BasePresenter implements IRedditsListListener, IRedditsItemDataPresenter {

    private IRedditsListView mRedditsListView;
    private RedditsListBL mRedditsListBL;


    public RedditsItemDataPresenter(IRedditsListView redditsListView) {
        this.mRedditsListView = redditsListView;
        this.mRedditsListBL = new RedditsListBL(this);
    }





    @Override
    public void getItemSuccess(RedditItem redditItem) {
        this.mRedditsListView.successItem(redditItem);
    }

    @Override
    public void getItemFaliure(Error error) {
        this.mRedditsListView.failItem(error);
    }

    @Override
    public void getListSuccess(List<RedditItem> mRedditList) {
        this.mRedditsListView.success(mRedditList);
    }

    @Override
    public void getListFaliure(Error error) {

        this.mRedditsListView.fail(error);
    }

    @Override
    public void getItem(String id) {
        this.mRedditsListBL.getItem( id);
    }


}
