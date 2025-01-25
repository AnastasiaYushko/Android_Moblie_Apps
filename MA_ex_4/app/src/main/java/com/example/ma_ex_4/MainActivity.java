package com.example.ma_ex_4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        constraintLayout = findViewById(R.id.main);
        Button yellowButton = findViewById(R.id.button_yellow);

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.yellow_string);
                constraintLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.yellowColor));
            }
        });
    }

    public void onRedButtonClick(View view) {
        textView = findViewById(R.id.textView);
        constraintLayout = findViewById(R.id.main);
        textView.setText(R.string.red_string);
        constraintLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.redColor));
    }

    public void onGreenButtonClick(View view) {
        textView = findViewById(R.id.textView);
        constraintLayout = findViewById(R.id.main);
        textView.setText(R.string.green_string);
        constraintLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.greenColor));
    }
}