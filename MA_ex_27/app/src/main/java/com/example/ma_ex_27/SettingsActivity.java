package com.example.ma_ex_27;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // загружаем предпочтения из ресурсов
        addPreferencesFromResource(R.xml.preferences);
    }
}