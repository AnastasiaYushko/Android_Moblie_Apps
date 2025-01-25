package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends Activity {

    public final static String THIEF = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void onRadioClick(View v) {
        Intent answerIntent = new Intent();

        if (v.getId() == R.id.radioDog) {
            answerIntent.putExtra(THIEF, "Веселая собака");
        } else if (v.getId() == R.id.radioCrow) {
            answerIntent.putExtra(THIEF, "Белая ворона");
        } else if (v.getId() == R.id.radioCat) {
            answerIntent.putExtra(THIEF, "Пушистый котик");
        } else {
            return;
        }

        setResult(RESULT_OK, answerIntent);
        finish();
    }

}
