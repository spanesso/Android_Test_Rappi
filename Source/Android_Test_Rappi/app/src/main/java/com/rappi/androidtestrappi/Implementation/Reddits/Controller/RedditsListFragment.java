package com.rappi.androidtestrappi.Implementation.Reddits.Controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rappi.androidtestrappi.App.Base.MyApp;
import com.rappi.androidtestrappi.Implementation.Main.Controller.MainActivity;
import com.rappi.androidtestrappi.Implementation.Models.Error;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;
import com.rappi.androidtestrappi.Implementation.Reddits.Presenter.IRedditsListPresenter;
import com.rappi.androidtestrappi.Implementation.Reddits.Presenter.RedditsListPresenter;
import com.rappi.androidtestrappi.R;

import com.rappi.androidtestrappi.Utils.InternetConection.ConnectionErrorFragment;
import com.rappi.androidtestrappi.Utils.InternetConection.InternetStatusProvider;

import java.util.List;


public class RedditsListFragment extends Fragment implements IRedditsListView {

    private RecyclerView mRedditsListRecyclerView;
    private ProgressDialog mProgressDialog;
    private RedditsListRecyclerViewAdapter mRedditsListRecyclerViewAdapter;
    private LinearLayoutManager mLayoutManager;
    private IRedditsListPresenter mRedditsListPresenter;
    private List<RedditItem> mRedditList;

    @Override
    public void successItem(RedditItem redditItem) {

    }

    @Override
    public void failItem(Error error) {

    }

    private InternetStatusProvider internetStatusProvider;
    ActionBar actionBar;


    public RedditsListFragment() {
    }

    public static RedditsListFragment newInstance(String param1, String param2) {
        RedditsListFragment fragment = new RedditsListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

         mProgressDialog = ProgressDialog.show(MyApp.getCurrentActivity(),getResources().getString(R.string.load_list_dialog_title),  getResources().getString(R.string.load_list_dialog_desc), true);

        actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        internetStatusProvider = new InternetStatusProvider(MyApp.getContext());


        this.mLayoutManager = new LinearLayoutManager(MyApp.getCurrentActivity());
        this.mRedditsListRecyclerViewAdapter = new RedditsListRecyclerViewAdapter();

        this.mRedditsListPresenter = new RedditsListPresenter(this);



        if (this.mRedditsListPresenter.isListFull()) {
            this.mRedditsListPresenter.getLocalList();
        } else {

            if (internetStatusProvider.isInternetStablishConnection()) {
                this.mRedditsListPresenter.getList();
            } else {
                Fragment fragment = new ConnectionErrorFragment();

                String title = getResources().getString(R.string.menu_connection_error);


                if (fragment != null) {

                  actionBar.setTitle(title);
                    FragmentTransaction fragmentTransaction = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reddits_list, container, false);

        actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        this.mRedditsListRecyclerView = (RecyclerView) view.findViewById(R.id.redditsListRecyclerView);
        this.mRedditsListRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRedditsListRecyclerView.setHasFixedSize(true);
        this.mRedditsListRecyclerView.setAdapter(this.mRedditsListRecyclerViewAdapter);
     //   mProgressDialog.show();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {

        menuInflater.inflate(R.menu.main, menu);

        super.onCreateOptionsMenu(menu, menuInflater);
    }

    private void showRedditList(List<RedditItem> redditList) {

       mProgressDialog.dismiss();
     mProgressDialog.hide();
        Log.i("*******","------------>");
        if (redditList.size() > 0) {
         mProgressDialog.dismiss();
            this.mRedditsListRecyclerViewAdapter.setRedditsList(redditList);
            this.mRedditsListRecyclerViewAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void success(List<RedditItem> redditList) {
         this.mRedditList = redditList;
         this.showRedditList(redditList);
    }

    @Override
    public void fail(Error error) {
        Log.e("RedditsList", error.getMessage());
    }


}
