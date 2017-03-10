package com.rappi.androidtestrappi.App.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {



    protected OnBackPressedListener onBackPressedListener;

    public interface OnBackPressedListener {
        void doBack();
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getSupportActionBar().hide();
        MyApp.setCurrentActivity(this);

    }
    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null)
            onBackPressedListener.doBack();
        else
            super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        onBackPressedListener = null;
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();

        MyApp.setCurrentActivity(this);
        MyApp.getInstance().setIsInForeground(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApp.getInstance().setIsInForeground(false);
    }
}