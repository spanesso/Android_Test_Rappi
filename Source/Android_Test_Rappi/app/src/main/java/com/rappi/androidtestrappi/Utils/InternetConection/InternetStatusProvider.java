package com.rappi.androidtestrappi.Utils.InternetConection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.rappi.androidtestrappi.App.Base.MyApp;
import com.rappi.androidtestrappi.R;


/**
 * Created by Spanesso on 25/08/2016.
 */
public class InternetStatusProvider {
    private static Context mContext;
    public InternetStatusProvider(Context context) {
        this.mContext = context;
    }

    public static Boolean isInternetStablishConnection() {
           boolean haveConnectedWifi = false;
           boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                   haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return  haveConnectedWifi || haveConnectedMobile;
    }

}
