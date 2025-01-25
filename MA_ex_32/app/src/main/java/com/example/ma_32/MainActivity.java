package com.example.ma_32;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = findViewById(R.id.textView);
    }

    public void onClick_1(View view) {
        MyDialogFragment_1 myDialogFragment = new MyDialogFragment_1();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        myDialogFragment.show(transaction, "dialog");
    }

    public void onClick_2(View view) {
        MyDialogFragment_2 myDialogFragment = new MyDialogFragment_2();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        myDialogFragment.show(transaction, "dialog");
    }

    public void onClick_3(View view) {
        MyDialogFragment_3 myDialogFragment = new MyDialogFragment_3();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        myDialogFragment.show(transaction, "dialog");
    }

    public void onClick_4(View view) {
        MyDialogFragment_4 myDialogFragment = new MyDialogFragment_4();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        myDialogFragment.show(transaction, "dialog");
    }

    public void onClick_5(View view) {
        MyDialogFragment_5 myDialogFragment = new MyDialogFragment_5();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        myDialogFragment.show(transaction, "dialog");
    }

    public void onClick_6(View view) {
        MyDialogFragment_6 myDialogFragment = new MyDialogFragment_6();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        myDialogFragment.show(transaction, "dialog");
    }

    public void onClick_7(View view) {
        MyDialogFragment_7 myDialogFragment = new MyDialogFragment_7();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        myDialogFragment.show(transaction, "dialog");
    }

    public void onClick_8(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Автоматическое закрытие окна");
        builder.setMessage("Через пять секунд это окно закроется автоматически!");
        builder.setCancelable(true);

        final AlertDialog dlg = builder.create();

        dlg.show();

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                dlg.dismiss();
                timer.cancel();
            }
        }, 5000);
    }

    public void onClick_9(View v) {
        showRatingDialog();
    }

    public void showRatingDialog() {

        final AlertDialog.Builder ratingdialog = new AlertDialog.Builder(this);

        ratingdialog.setIcon(android.R.drawable.btn_star_big_on);
        ratingdialog.setTitle("Голосуем за любимого кота!");

        View linearlayout = getLayoutInflater().inflate(R.layout.ratingdialog, null);
        ratingdialog.setView(linearlayout);

        final RatingBar rating = linearlayout.findViewById(R.id.ratingbar);

        ratingdialog.setPositiveButton("Готово",
                        (dialog, which) -> {
                            txtView.setText(String.valueOf(rating.getRating()));
                            dialog.dismiss();
                        })

                .setNegativeButton("Отмена",
                        (dialog, id) -> dialog.cancel());

        ratingdialog.create();
        ratingdialog.show();
    }
}