package com.example.user.surveyapp;

import android.util.Log;

/**
 * Created by User on 21/08/2016.
 */
public class debugLoger {

    public  debugLoger(String msg){
        Log.e(TAG,msg);
    }

    public static String TAG = "MYTAG";

    public static void log(String debugMessage){
        Log.e(TAG,debugMessage);
    }
}
