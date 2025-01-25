package com.example.ma_ex_12;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);
        ImageView imageView = findViewById(R.id.imageView);

        button.setOnClickListener(viewClickListener);
        textView.setOnClickListener(viewClickListener);
        imageView.setOnClickListener(viewClickListener);
    }

    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }
    };

    private void showPopupMenu(View v) {
        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);

        // Создаём PopupMenu
        PopupMenu popupMenu2 = new PopupMenu(this, button);
        popupMenu2.inflate(R.menu.popupmenu);

        // Обработчик выбора элементов меню
        popupMenu2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.red) {
                    textView.setBackground(new ColorDrawable(Color.RED));
                    textView.setText("Вы выбрали красный цвет");
                } else if (id == R.id.yellow) {
                    textView.setBackground(new ColorDrawable(Color.YELLOW));
                    textView.setText("Вы выбрали жёлтый цвет");
                } else if (id == R.id.green) {
                    textView.setBackground(new ColorDrawable(Color.GREEN));
                    textView.setText("Вы выбрали зелёный цвет");
                }
                else if (id == R.id.submenu) {
                    textView.setBackground(new ColorDrawable(Color.YELLOW));
                    textView.setText("Вы выбрали ярко желтый цвет");
                }
                else{
                    textView.setText("");
                }
                return false;
            }
        });

        // Принудительное включение отображения иконок через рефлексию
        try {
            Field field = popupMenu2.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            Object menuHelper = field.get(popupMenu2);
            Class<?> classPopupHelper = Class.forName(menuHelper.getClass().getName());
            Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
            setForceIcons.invoke(menuHelper, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        popupMenu2.show();
    }
}
