package com.rappi.androidtestrappi.Implementation.Reddits.Controller;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rappi.androidtestrappi.App.Base.MyApp;
import com.rappi.androidtestrappi.Implementation.Models.RedditDataItem;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;
import com.rappi.androidtestrappi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sapanesso on 16/01/17.
 */

public class RedditsDataItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RedditDataItem> mRedditDataItemList;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    RedditsDataItemRecyclerViewAdapter() {
        this.mRedditDataItemList = new ArrayList();
    }

    public class RedditsItemDataViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewItemDataDetailContainer;
        TextView textViewKeyValue;
        TextView textViewTitleKey;

        public RedditsItemDataViewHolder(View redditsListItem) {
            super(redditsListItem);

            cardViewItemDataDetailContainer = (CardView) redditsListItem.findViewById(R.id.cardViewItemDataDetailContainer);
            textViewTitleKey = (TextView) redditsListItem.findViewById(R.id.textViewTitleKey);
            textViewKeyValue = (TextView) redditsListItem.findViewById(R.id.textViewKeyValue);
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
            View redditItemDataCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_data_item_detail, parent, false);
            RedditsItemDataViewHolder redditItemViewHolder = new RedditsItemDataViewHolder(redditItemDataCardView);
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

        if (holder instanceof RedditsItemDataViewHolder) {
            final RedditDataItem redditDataItem = this.mRedditDataItemList.get(position);

            ((RedditsItemDataViewHolder) holder).textViewTitleKey.setText(redditDataItem.getKey());
            ((RedditsItemDataViewHolder) holder).textViewKeyValue.setText(redditDataItem.getValue());
            ((RedditsItemDataViewHolder) holder).cardViewItemDataDetailContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String value = redditDataItem.getValue();

                    if(value.indexOf(".png") != -1 || value.indexOf(".jpg") != -1 ){
                        //Data is image
                        Log.e("**","image");
                    }else{
                        //Data is text
                    }
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mRedditDataItemList.size() > 0 ? mRedditDataItemList.size() + 1 : mRedditDataItemList.size();
    }

    public void setRedditsItemDataList(List<RedditDataItem> redditDataItemList) {
        this.mRedditDataItemList = redditDataItemList;
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
        return position == mRedditDataItemList.size();
    }

}
