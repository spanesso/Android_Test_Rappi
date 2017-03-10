package com.rappi.androidtestrappi.App.Base;
import android.app.Activity;
import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by sebastiangomez on 24/02/16.
 */
public class MyApp extends Application {

    static MyApp instance;
    static Activity mCurrentActivity;
    private boolean mIsInForeground;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);


    }

    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public static void setCurrentActivity(Activity mCurrentActivity) {
        MyApp.mCurrentActivity = mCurrentActivity;
    }

    public boolean isIsInForeground() {
        return mIsInForeground;
    }

    public void setIsInForeground(boolean isInForeground) {
        this.mIsInForeground = isInForeground;
    }

    public static boolean thereIsSession(Context context) {


        return false;
    }

    public static Realm getDefaultRealmInstance(){
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getInstance())
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Realm realm = Realm.getDefaultInstance();

        return realm;
    }


}
