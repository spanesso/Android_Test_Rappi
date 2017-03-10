package com.rappi.androidtestrappi.Implementation.Reddits.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rappi.androidtestrappi.App.Base.BaseActivity;
import com.rappi.androidtestrappi.App.Base.MyApp;
import com.rappi.androidtestrappi.Implementation.Models.Error;
import com.rappi.androidtestrappi.Implementation.Models.RedditDataItem;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;
import com.rappi.androidtestrappi.Implementation.Reddits.Presenter.IRedditsItemDataPresenter;
import com.rappi.androidtestrappi.Implementation.Reddits.Presenter.RedditsItemDataPresenter;
import com.rappi.androidtestrappi.Implementation.Reddits.Presenter.RedditsListPresenter;
import com.rappi.androidtestrappi.R;

import java.util.List;

public class RedditDataItemDetailActivity extends BaseActivity implements IRedditsListView {


    private RecyclerView mRedditsItemDataListRecyclerView;
private IRedditsItemDataPresenter mRedditsItemDataPresenter;
    private RedditsDataItemRecyclerViewAdapter mRedditsDataItemRecyclerViewAdapter;
    private LinearLayoutManager mLayoutManager;
    private    List<RedditDataItem> mRedditItemDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_item_detail);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.data_item));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);



        this.mLayoutManager = new LinearLayoutManager(MyApp.getCurrentActivity());
        this.mRedditsDataItemRecyclerViewAdapter = new RedditsDataItemRecyclerViewAdapter();
        this.mRedditsItemDataListRecyclerView = (RecyclerView)  findViewById(R.id.recyclerViewListDataItem);
        this.mRedditsItemDataListRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRedditsItemDataListRecyclerView.setHasFixedSize(true);
        this.mRedditsItemDataListRecyclerView.setAdapter(this.mRedditsDataItemRecyclerViewAdapter);


        this.mRedditsItemDataPresenter = new RedditsItemDataPresenter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String id = extras.getString("id");
            this.mRedditsItemDataPresenter.getItem(id);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void success(List<RedditItem> mRedditList) {

    }

    @Override
    public void fail(Error error) {

    }

    @Override
    public void successItem(RedditItem redditItem) {
         mRedditItemDataList = redditItem.getRedditDataItemList();
        this.mRedditsDataItemRecyclerViewAdapter.setRedditsItemDataList(mRedditItemDataList);
        this.mRedditsDataItemRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void failItem(Error error) {

    }
}
