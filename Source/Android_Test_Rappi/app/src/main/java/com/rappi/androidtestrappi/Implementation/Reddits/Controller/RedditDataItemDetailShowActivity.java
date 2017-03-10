package com.rappi.androidtestrappi.Implementation.Reddits.Controller;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.rappi.androidtestrappi.App.Base.BaseActivity;
import com.rappi.androidtestrappi.App.Base.MyApp;
import com.rappi.androidtestrappi.Implementation.Models.Error;
import com.rappi.androidtestrappi.Implementation.Models.RedditDataItem;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;
import com.rappi.androidtestrappi.Implementation.Reddits.Presenter.IRedditsItemDataPresenter;
import com.rappi.androidtestrappi.Implementation.Reddits.Presenter.RedditsItemDataPresenter;
import com.rappi.androidtestrappi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class RedditDataItemDetailShowActivity extends BaseActivity  {


    private ScrollView scrollViewDataDesc;
    private TextView itemDataDesc;
    private ImageViewTouch imgDataItemShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_item_detail_show);





        this.scrollViewDataDesc = (ScrollView)  findViewById(R.id.scrollViewDataDesc);
        this.itemDataDesc = (TextView)  findViewById(R.id.itemDataDesc);
        this.imgDataItemShow = (ImageViewTouch)  findViewById(R.id.imgDataItemShow);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String data = extras.getString("data");
            String type = extras.getString("type");

            showDataItemSelected(data,type);

        }
    }

    public void showDataItemSelected(String data, String type){
        if(type.equals("img")){

            this.imgDataItemShow.setVisibility(View.VISIBLE);
            this.scrollViewDataDesc.setVisibility(View.INVISIBLE);
            Picasso.with(MyApp.getContext()).load(data).into( this.imgDataItemShow);
        }else{
            this.imgDataItemShow.setVisibility(View.INVISIBLE);
            this.itemDataDesc.setText(data);
            this.scrollViewDataDesc.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }




}
