package com.rappi.androidtestrappi.Implementation.Reddits.BusinessLayer;

import com.rappi.androidtestrappi.Implementation.Models.Error;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;
import com.rappi.androidtestrappi.Implementation.Reddits.Presenter.IRedditsListListener;
import com.rappi.androidtestrappi.App.Base.BaseBL;
import com.rappi.androidtestrappi.Implementation.Reddits.Repository.IRedditsListResponseHandler;
import com.rappi.androidtestrappi.Implementation.Reddits.Repository.RedditsListRepository;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by sapanesso on 16/01/17.
 */

public class RedditsListBL extends BaseBL implements IRedditsListResponseHandler {


    private IRedditsListListener mRedditsListListener;
    private List<RedditItem> mRedditList;

    public RedditsListBL(IRedditsListListener redditsListListener) {
        mRedditsListListener = redditsListListener;
        mRedditList = new ArrayList();
    }



    public void getRedditsList() {
        RedditsListRepository.getRedditsListService(this);
    }
    public void getItem(String id) {
        RedditsListRepository.getItem(id,this);
    }


    public void getLocalList() {
        RedditsListRepository.getRedditsLocalList(this);
    }

    public Boolean isListFull() {
       return RedditsListRepository.isListFull();
    }



    @Override
    public void onAPIResponse(Error error, List<RedditItem> redditList) {
        if (error == null) {
            mRedditList = redditList;
            mRedditsListListener.getListSuccess(mRedditList);
        } else {
            mRedditsListListener.getListFaliure(error);
        }
    }

    @Override
    public void onAPIResponseItem(Error error,  RedditItem redditItem) {
        if (error == null) {
            mRedditsListListener.getItemSuccess(redditItem);
        } else {
            mRedditsListListener.getItemFaliure(error);
        }
    }
}
