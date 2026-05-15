package com.example.eventfinder.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "EventFinderSession";
    private static final String KEY_USER_ID = "user_id";

    //just general functions to manage the user_id/session
    //"sharedpreferecnes is a built in class". It acts like s file storing system
    //"EventFinderSession" is the name of the drawer and "user_id" is the name
    //of the file inside that drawer.
    public static void saveUserId(Context context, int userId) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit(); //we can't edit the "file directly. we need to use an editor"
        editor.putInt(KEY_USER_ID, userId);
        editor.apply();
    }


    public static int getUserId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(KEY_USER_ID, -1);
    }

    //clearing memeory when users logs out
    public static void logout(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}