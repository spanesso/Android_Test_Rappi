package com.rappi.androidtestrappi.Implementation.LaunchScreen.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.gson.Gson;
import com.rappi.androidtestrappi.App.Base.BaseActivity;
import com.rappi.androidtestrappi.App.Base.Constants;
import com.rappi.androidtestrappi.App.Base.MyApp;
import com.rappi.androidtestrappi.App.Base.PreferencesManager;

import com.rappi.androidtestrappi.Implementation.Main.Controller.MainActivity;
import com.rappi.androidtestrappi.R;

/**
 * Created by Sebastian on 10/02/16.
 */
public class LaunchScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        Handler HANDLER = new Handler();
        HANDLER.postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
