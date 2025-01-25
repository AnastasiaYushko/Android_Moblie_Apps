package com.example.ma_ex_2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView helloTextView;
    private EditText editText;
    private String text_1;
    private String text_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        text_1 = getResources().getString(R.string.hello_kitty);
        text_2 = getResources().getString(R.string.hello);
        helloTextView = findViewById(R.id.textView);
        ImageButton imageButton = findViewById(R.id.imageButton);
        editText = findViewById(R.id.editText);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().isEmpty()) {
                    helloTextView.setText(text_1);
                } else {
                    String all_text = text_2 + " " + editText.getText() + "!";
                    helloTextView.setText(all_text);
                }
            }
        });
    }
}