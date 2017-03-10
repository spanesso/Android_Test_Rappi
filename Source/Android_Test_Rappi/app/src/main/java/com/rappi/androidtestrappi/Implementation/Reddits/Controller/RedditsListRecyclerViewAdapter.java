package com.rappi.androidtestrappi.Implementation.Reddits.Controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.rappi.androidtestrappi.App.Base.MyApp;

import com.rappi.androidtestrappi.Implementation.Models.RedditDataItem;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;
import com.rappi.androidtestrappi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

/**
 * Created by sapanesso on 16/01/17.
 */

public class RedditsListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RedditItem> mRedditList;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    RedditsListRecyclerViewAdapter() {
        this.mRedditList = new ArrayList();
    }

    public class RedditsListViewHolder extends RecyclerView.ViewHolder {

        CardView mRedditsListCardView;
        ImageView imageViewItemLogo;
        TextView textViewItemTitle;
        TextView textViewItemDesc;

        public RedditsListViewHolder(View redditsListItem) {
            super(redditsListItem);

            mRedditsListCardView = (CardView) redditsListItem.findViewById(R.id.redditItemCardView);
            imageViewItemLogo = (ImageView) redditsListItem.findViewById(R.id.imageViewItemLogo);
            textViewItemTitle = (TextView) redditsListItem.findViewById(R.id.textViewItemTitle);
            textViewItemDesc = (TextView) redditsListItem.findViewById(R.id.textViewItemDesc);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        FooterViewHolder(View footerCardView) {
            super(footerCardView);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View redditItemCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_reddit_tip, parent, false);
            RedditsListViewHolder redditItemViewHolder = new RedditsListViewHolder(redditItemCardView);
            return redditItemViewHolder;
        } else if (viewType == TYPE_FOOTER) {
            View footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_list, parent, false);
            FooterViewHolder footerViewHolder = new FooterViewHolder(footerView);
            return footerViewHolder;
        }

        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof RedditsListViewHolder) {
            final RedditItem redditItem = this.mRedditList.get(position);

            List<RedditDataItem> itemDataList = redditItem.getRedditDataItemList();
            String title = "";
            String desc = "";
            String img = "";


            for (int i = 0; i < itemDataList.size(); i++) {
                RedditDataItem itemData = itemDataList.get(i);

                if (itemData.getKey().equals("display_name")) {
                    title = itemData.getValue();
                }
                if (itemData.getKey().equals("public_description")) {
                    desc = itemData.getValue();
                }
                if (itemData.getKey().equals("icon_img")) {
                    img = itemData.getValue();
                }
            }


            ((RedditsListViewHolder) holder).textViewItemTitle.setText(title);
            ((RedditsListViewHolder) holder).textViewItemDesc.setText(desc);

            if(!img.isEmpty()) {
                Picasso.with(MyApp.getContext()).load(img).into(((RedditsListViewHolder) holder).imageViewItemLogo);
            }
            ((RedditsListViewHolder) holder).mRedditsListCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MyApp.getContext(),RedditDataItemDetailActivity.class);
                    intent.putExtra("id",redditItem.getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApp.getContext().startActivity(intent);
                }
            });


        }


    }


    @Override
    public int getItemCount() {
        return mRedditList.size() > 0 ? mRedditList.size() + 1 : mRedditList.size();
    }

    public void setRedditsList(List<RedditItem> redditItemList) {
        this.mRedditList = redditItemList;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    private boolean isPositionFooter(int position) {
        return position == mRedditList.size();
    }

}
