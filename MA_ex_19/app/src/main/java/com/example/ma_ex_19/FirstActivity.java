package com.example.ma_ex_19;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.firstactivity_main);
    }

    public void onClick_1(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClick_2(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.alexanderklimov.ru/android/"));
        startActivity(Intent.createChooser(intent, "Мяу..."));
    }
}
