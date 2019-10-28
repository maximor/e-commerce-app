package pucmm.temas.especiales.e_commerce_app.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import pucmm.temas.especiales.e_commerce_app.LoginActivity;
import pucmm.temas.especiales.e_commerce_app.entities.User;

public class UserSession {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "UserSessionPref";

    public UserSession(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

//    create login session
    public void createLoginSession(long id, String email, String user, String name, long token){
        editor.putBoolean("isLoggedIn", true);
        editor.putLong("id", id);
        editor.putString("email", email);
        editor.putString("user", user);
        editor.putString("name", name);
        editor.putLong("token", token);
        editor.commit();
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent intent = new Intent(context, LoginActivity.class);
            //close all activities
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            //add new flag to start a new activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //starts login activity
            context.startActivity(intent);
        }
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }

    public void logoutUser(){
        //clear all data from sharedPreferences
        editor.putBoolean("isLoggedIn", false);
        editor.commit();

        // After logout redirect user to Login Activity
        Intent intent = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(intent);
    }

    public User getUserInformation(){
        User user = new User();
        user.setId(sharedPreferences.getLong("id", -1));
        user.setEmail(sharedPreferences.getString("email", null));
        user.setUser(sharedPreferences.getString("user", null));
        user.setName(sharedPreferences.getString("name", null));
        user.setToken(sharedPreferences.getLong("token", -1));
        return user;
    }
}

