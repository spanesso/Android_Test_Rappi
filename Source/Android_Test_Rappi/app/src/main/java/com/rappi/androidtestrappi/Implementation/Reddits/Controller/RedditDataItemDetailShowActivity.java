package com.rappi.androidtestrappi.Implementation.Reddits.Controller;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.rappi.androidtestrappi.App.Base.BaseActivity;
import com.rappi.androidtestrappi.App.Base.MyApp;
import com.rappi.androidtestrappi.Implementation.Models.Error;
import com.rappi.androidtestrappi.Implementation.Models.RedditDataItem;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;
import com.rappi.androidtestrappi.Implementation.Reddits.Presenter.IRedditsItemDataPresenter;
import com.rappi.androidtestrappi.Implementation.Reddits.Presenter.RedditsItemDataPresenter;
import com.rappi.androidtestrappi.R;

import java.util.List;

public class RedditDataItemDetailShowActivity extends BaseActivity  {


    private RecyclerView mRedditsItemDataListRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_item_detail);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.data_item_data));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);




        this.mRedditsItemDataListRecyclerView = (RecyclerView)  findViewById(R.id.recyclerViewListDataItem);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String data = extras.getString("data");
            String type = extras.getString("type");

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



}
