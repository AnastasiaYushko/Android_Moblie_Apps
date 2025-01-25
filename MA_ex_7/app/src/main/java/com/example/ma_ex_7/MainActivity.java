package com.example.ma_ex_7;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_COUNT = "0";
    private TextView textView;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Восстановление счётчика
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(KEY_COUNT, 0);
        }

        initViews();
        updateCounterText();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, counter); // Сохраняем значение счётчика
    }

    private void initViews() {
        textView = findViewById(R.id.crow);

        // Кнопка увеличения счётчика
        Button buttonCounter = findViewById(R.id.button2);
        buttonCounter.setOnClickListener(v -> {
            counter++;
            updateCounterText();
        });

        // Кнопка смены ориентации
        Button buttonSwitchOrientation = findViewById(R.id.button1);
        buttonSwitchOrientation.setOnClickListener(v -> {
            int currentOrientation = getResources().getConfiguration().orientation;
            if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
    }

    private void updateCounterText() {
        if (counter > 0) {
            textView.setText("Я насчитал " + counter + " ворон!");
        } else {
            textView.setText("");
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Интерфейс обновляется при смене ориентации
        setContentView(R.layout.activity_main);
        initViews();
        updateCounterText();
    }
}

