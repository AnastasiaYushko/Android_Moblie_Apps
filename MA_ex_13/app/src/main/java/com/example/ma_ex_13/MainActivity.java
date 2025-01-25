package com.example.ma_ex_13;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.Settings;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Settings.System.canWrite(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }


        SeekBar backLightSeekBar = findViewById(R.id.seekBar);
        final TextView settingTextView = findViewById(R.id.textViewSetting);

        backLightSeekBar
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        float backLightValue = (float) progress / 100;
                        settingTextView.setText(String.valueOf(backLightValue));

                        // Изменяем яркость окна
                        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                        layoutParams.screenBrightness = backLightValue;
                        getWindow().setAttributes(layoutParams);

                        // Изменяем системную яркость
                        if (Settings.System.canWrite(MainActivity.this)) {
                            Settings.System.putInt(getContentResolver(),
                                    Settings.System.SCREEN_BRIGHTNESS,
                                    progress);
                        }
                    }


                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
    }


    public void button_setting_onClick(View v) {
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int screenWidth = point.x;
        int screenHeight = point.y;

        TextView textView = findViewById(R.id.text_settings);

        try {
            // Системная яркость экрана
            int curBrightnessValue = Settings.System.getInt(
                    getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS
            );

            // Вывести информацию о текущей яркости
            String info = "Ширина: " + screenWidth + ";\n"
                    + "Высота: " + screenHeight + ";\n"
                    + "Яркость экрана: " + curBrightnessValue + ";";

            textView.setText(info);

        } catch (Settings.SettingNotFoundException e) {
            textView.setText("Не удалось получить текущую яркость экрана.");
            e.printStackTrace();
        }
    }


    public void setting_onClick(View v) {
        Intent intent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}