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
//    //this method will store the user data in shared preferences
//    public void userLogin (User user) {
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(KEY_ID, user.getId());
//        editor.putString(KEY_USERNAME, user.getUsername());
//        editor.putString(KEY_EMAIL, user.getEmail());
//        editor.putString(KEY_GENDER, user.getGender());
//        editor.putString(KEY_IMAGES, user.getImages());
//        editor.apply();
//    }
//
//    //this method will checker whether user is already logged in or not
//    public boolean isLoggedIn() {
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences (SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(KEY_USERNAME,null) != null;
//    }
//    //this method will give the logged in user
//    public User getUser() {
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences (SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return new User(
//                sharedPreferences.getString(KEY_ID, null),
//                sharedPreferences.getString(KEY_USERNAME, null),
//                sharedPreferences.getString(KEY_EMAIL, null),
//                sharedPreferences.getString(KEY_GENDER, null),
//                sharedPreferences.getString(KEY_IMAGES, null),
//                sharedPreferences.getString(KEY_FNAME, null)
//        );
//    }
}
