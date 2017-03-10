package com.rappi.androidtestrappi.Implementation.Reddits.Repository;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.rappi.androidtestrappi.App.Base.Constants;
import com.rappi.androidtestrappi.Implementation.Models.Error;
import com.rappi.androidtestrappi.Implementation.Models.RedditDataItem;
import com.rappi.androidtestrappi.Implementation.Models.RedditItem;
import com.rappi.androidtestrappi.App.Base.BaseRepository;
import com.rappi.androidtestrappi.App.Base.MyApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by sapanesso on 16/01/17.
 */

public class RedditsListRepository extends BaseRepository {


    public static Boolean isListFull() {
        Realm realm = MyApp.getDefaultRealmInstance();
        RealmResults<RedditItem> results = realm.where(RedditItem.class).findAllSorted("id", Sort.ASCENDING);

        if(results.size() > 0){
            return true;
        }else{
            return false;
        }
    }
    public static void getRedditsListService(final IRedditsListResponseHandler handler) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_SERVER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        final Realm mRealm = MyApp.getDefaultRealmInstance();


                        Log.e("Login", "response: " + response);

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONObject jsonObjData = jsonObj.getJSONObject("data");
                            final JSONArray jsonArrayData = jsonObjData.getJSONArray("children");


                            mRealm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    try {

                                        for (int i = 0; i < jsonArrayData.length(); i++) {

                                            String id = generateUniqueId();

                                            JSONObject children = jsonArrayData.getJSONObject(i);
                                            JSONObject childrenData = children.getJSONObject("data");
                                            JSONArray childrenDataKeys = childrenData.names();

                                            RealmList<RedditDataItem> mRedditDataItemList = new RealmList<>();

                                            for (int j = 0; j < childrenDataKeys.length(); j++) {

                                                String idDataItem = generateUniqueId();
                                                String key = childrenDataKeys.getString(j);

                                                RedditDataItem redditDataItem = mRealm.createObject(RedditDataItem.class);
                                                redditDataItem.setId(idDataItem);
                                                redditDataItem.setKey(key);
                                                redditDataItem.setValue(childrenData.getString(key));

                                                mRedditDataItemList.add(redditDataItem);
                                            }

                                            RedditItem redditItem = mRealm.createObject(RedditItem.class);
                                            redditItem.setId(id);
                                            redditItem.setRedditDataItemList(mRedditDataItemList);

                                            Log.i("**", "----->" + redditItem.getId());

                                            mRealm.copyToRealmOrUpdate(redditItem);

                                            getRedditsLocalList(handler);
                                        }

                                    } catch (JSONException e) {

                                    }
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Error responseError = new Error("400", "Error de conexión");
                        handler.onAPIResponse(responseError, null);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(MyApp.getContext());
        requestQueue.add(stringRequest);
    }

    public static void getRedditsLocalList(final IRedditsListResponseHandler handler) {

        List<RedditItem> redditItemList = null;

        Realm realm = MyApp.getDefaultRealmInstance();
        RealmResults<RedditItem> results = realm.where(RedditItem.class).findAllSorted("id", Sort.ASCENDING);
        redditItemList = results.subList(0, results.size());

        handler.onAPIResponse(null,redditItemList );
    }

    public static void getItem(String id, final IRedditsListResponseHandler handler) {
        List<RedditItem> redditItemList = null;
        Realm realm = MyApp.getDefaultRealmInstance();



        RedditItem item = realm.where(RedditItem.class).equalTo("id",id).findFirst();
        handler.onAPIResponseItem(null,item );
    }

    private static String generateUniqueId() {
        String ts = String.valueOf(System.currentTimeMillis());
        String rand = UUID.randomUUID().toString() + UUID.randomUUID().toString();

        return ts + rand;
    }
}
