package com.example.latoris.datapractice;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final static String SharedPreferencesFilesname = "config";
    private final static String key_UserName = "username";
    private final static String key_PassWord = "password";

    SharedPreferences preferences;
    preferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences= getSharedPreferences(SharedPreferencesFilesname, MODE_PRIVATE);
        editor = preferences.edit();
    }
}
