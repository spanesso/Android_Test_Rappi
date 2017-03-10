package com.rappi.androidtestrappi.Implementation.Main.Controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.rappi.androidtestrappi.Implementation.Reddits.Controller.RedditsListFragment;
import com.rappi.androidtestrappi.Utils.InternetConection.ConnectionErrorFragment;
import com.squareup.picasso.Picasso;
import com.rappi.androidtestrappi.App.Base.BaseActivity;
import com.rappi.androidtestrappi.App.Base.Constants;
import com.rappi.androidtestrappi.App.Base.PreferencesManager;
import com.rappi.androidtestrappi.Implementation.DashBoard.Controller.DashBoardFragment;
import com.rappi.androidtestrappi.R;


import com.rappi.androidtestrappi.Utils.Notifications.NotificationUtils;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView txtRegId, txtMessage;
    private ActionBar actionBar;
    private ImageView userProfileImageView;
    private TextView userProfileNameTextView;
    private TextView userProfileMailTextView;
    private static Boolean isInDashBoardView = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.actionBar = getSupportActionBar();
        if (this.actionBar != null) {
            this.actionBar.setTitle(R.string.app_name);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.inflateHeaderView(R.layout.nav_header_main);


        this.showDashBoardPanel();
    }



    public   void chagenActionBarTitle(String title){
        if (this.actionBar != null) {
            this.actionBar.setTitle(title);
        }
    }



    public void showDashBoardPanel() {
        Fragment fragment = new DashBoardFragment();

        if (fragment != null) {

            this.isInDashBoardView = true;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.commit();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //  if (id == R.id.action_settings) {
        //    return true;
        //  }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.menu_dashboard) {
            this.actionBar.setTitle(R.string.menu_dashboard);
            this.isInDashBoardView = true;
            fragment = new DashBoardFragment();
        } else if (id == R.id.menu_list) {
            this.actionBar.setTitle(R.string.menu_list);
            this.isInDashBoardView = true;
            fragment = new RedditsListFragment();
        }

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {

        super.onPause();
    }




    @Override
    public void onBackPressed() {
        Log.i("***", "**************************  onBackPressed   *************************");
        if (this.isInDashBoardView) {
            finish();
        } else {
            showDashBoardPanel();
        }
    }
}
