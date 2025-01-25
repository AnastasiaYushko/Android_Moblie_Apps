package com.example.ma_ex_26;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int IDM_PREF = 103;

    private TextView myEdit; // текстовое поле

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        myEdit = findViewById(R.id.editText_1);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // читаем размер шрифта из EditTextPreference
        float fSize = Float.parseFloat(
                prefs.getString(getString(R.string.pref_size), "20"));
        // применяем настройки в текстовом поле
        myEdit.setTextSize(fSize);

        // читаем стили текста из ListPreference
        String regular = prefs.getString(getString(R.string.pref_style), "");
        int typeface = Typeface.NORMAL;

        if (regular.contains("Полужирный"))
            typeface += Typeface.BOLD;

        if (regular.contains("Курсив"))
            typeface += Typeface.ITALIC;

        // меняем настройки в EditText
        //myEdit.setTextSize(fSize);
        myEdit.setTypeface(null, typeface);

        // читаем цвет текста из CheckBoxPreference
        // и суммируем значения для получения дополнительньк цветов текста
        int color = Color.BLACK;
        if (prefs.getBoolean(getString(R.string.pref_color_red), false))
            color += Color.RED;
        if (prefs.getBoolean(getString(R.string.pref_color_green), false))
            color += Color.GREEN;
        if (prefs.getBoolean(getString(R.string.pref_color_blue), false))
            color += Color.BLUE;

        myEdit.setTextColor(color);
    }

    // Создаем меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, IDM_PREF, Menu.NONE, "Настройки");
        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == IDM_PREF) {
            Intent intent = new Intent();
            intent.setClass(this, MyPreferenceActivity.class);
            startActivity(intent);
        } else {
            return false;
        }
        return true;
    }
}