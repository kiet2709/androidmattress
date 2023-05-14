package com.nhom4nguoi.ecommerce.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.nhom4nguoi.ecommerce.model.User;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "nhom4nguoi";
    private static SharedPrefManager instance;
    private static Context ctx;

    public SharedPrefManager(Context context) {
        ctx = context;
    }
    public static synchronized  SharedPrefManager getInstance(Context context){
        if(instance == null){
            instance = new SharedPrefManager(context);
        }
        return instance;
    }
    public void storeJWT(String jwt){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", "Bearer " + jwt);
        editor.apply();
    }
    public String getJWT(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String jwt = sharedPreferences.getString("token", null);
        return jwt;
    }

}
