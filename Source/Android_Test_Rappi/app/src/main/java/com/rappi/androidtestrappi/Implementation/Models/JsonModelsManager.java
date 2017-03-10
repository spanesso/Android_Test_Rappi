package com.rappi.androidtestrappi.Implementation.Models;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rappi.androidtestrappi.App.Base.MyApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by sapanesso on 15/01/17.
 */

public class JsonModelsManager {

    private Context mContext;
    private Realm mRealm;

    public JsonModelsManager(Context context) {
        this.mRealm = MyApp.getDefaultRealmInstance();
        this.mContext = context;
    }








}
