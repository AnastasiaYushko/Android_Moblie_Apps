package com.example.ma_ex_26;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MyPreferenceActivity extends PreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String color_string = "#665858";
        findViewById(android.R.id.list).setBackgroundColor(Color.parseColor(color_string));
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MyPrefsFragment())
                .commit();
    }
}
