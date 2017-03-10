package com.rappi.androidtestrappi.Implementation.Reddits.Presenter;

import com.rappi.androidtestrappi.Implementation.Models.Error;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;
import com.rappi.androidtestrappi.Implementation.Reddits.BusinessLayer.RedditsListBL;
import com.rappi.androidtestrappi.Implementation.Reddits.Controller.IRedditsListView;
import com.rappi.androidtestrappi.App.Base.BasePresenter;

import java.util.List;

/**
 * Created by sapanesso on 16/01/17.
 */

public class RedditsListPresenter extends BasePresenter implements IRedditsListListener, IRedditsListPresenter {

    private IRedditsListView mRedditsListView;
    private RedditsListBL mRedditsListBL;


    public RedditsListPresenter(IRedditsListView redditsListView) {
        this.mRedditsListView = redditsListView;
        this.mRedditsListBL = new RedditsListBL(this);
    }

    @Override
    public Boolean isListFull() {
      return  this.mRedditsListBL.isListFull();
    }

    @Override
    public void getLocalList() {
        this.mRedditsListBL.getLocalList();
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
    public void getList() {
        this.mRedditsListBL.getRedditsList();
    }

    @Override
    public void getItemSuccess(RedditItem redditItem) {

    }

    @Override
    public void getItemFaliure(Error error) {

    }
}
