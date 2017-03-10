package com.rappi.androidtestrappi.App.Base;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sebastian on 10/02/16.
 */
public class PreferencesManager {

     public static  String PREFERENCES_TOKEN="Rappi-Preferences";

    /**
     * Set Preferences
     */

    public static void cleanUserFromPreferences(Context context) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.remove(Constants.Preferences.CURRENT_USER);
        editor.commit();
    }

    public static SharedPreferences.Editor getPreferencesEditor(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_TOKEN,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return editor;
    }


    public static void saveString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putInt(key, value);
        editor.commit();
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void saveFloat(Context context, String key, float value) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putFloat(key, value);
        editor.commit();
    }

    public static void saveLong(Context context, String key, long value) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * Get Preferences
     */

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_TOKEN,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static int getInt(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_TOKEN,
                Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, -404);
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_TOKEN,
                Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static float getFloat(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_TOKEN,
                Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, -404);
    }

    public static long getLong(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_TOKEN,
                Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, -404);
    }
}
