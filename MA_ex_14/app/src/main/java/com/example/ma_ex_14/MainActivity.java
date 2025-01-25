package com.example.ma_ex_14;

import android.app.Notification;
import android.app.PendingIntent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.widget.Toast;
import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "Cat channel";
    private static final int PERMISSION_REQUEST_CODE = 100;
    private PendingIntent contentIntent;
    private int counter = 1; // Уникальный идентификатор уведомлений
    private int counter_pic = 1;

    String bigText = "Это я, почтальон Печкин. Принёс для вас посылку. "
            + "Только я вам её не отдам. Потому что у вас документов нету.\nУведомление #";

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем NotificationChannel, если это требуется
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Cat Reminders",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
            @Override
            public void onClick(View v) {

                Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
                contentIntent = PendingIntent.getActivity(MainActivity.this,
                        0, notificationIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.POST_NOTIFICATIONS)
                        == PackageManager.PERMISSION_GRANTED) {
                    sendNotification();
                } else {
                    // Запрашиваем разрешение
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.POST_NOTIFICATIONS},
                            PERMISSION_REQUEST_CODE);
                }
            }
        });
    }

    private void sendNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.icon_cat)
                        .setContentTitle("Посылка")
                        .setContentText(bigText)
                        .setPriority(Notification.PRIORITY_DEFAULT)
                        .setColor(Color.BLUE)
                        .setContentIntent(this.contentIntent)
                        .addAction(R.drawable.icon_cat, "Запустить активность", contentIntent)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText+counter))
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                R.drawable.hungrycat)) // большая картинка
                        .setAutoCancel(true); // автоматически закрыть уведомление после нажатия;

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(MainActivity.this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Используем уникальный идентификатор для каждого уведомления
        notificationManager.notify(counter++, builder.build());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendNotification();
            } else {
                Toast.makeText(this, "Разрешение на отправку уведомлений отклонено", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClick_button(View v){
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.cancelAll();
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void onClick_button_pic(View v){
        Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
        contentIntent = PendingIntent.getActivity(MainActivity.this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
            sendNotification_pic();
        } else {
            // Запрашиваем разрешение
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    PERMISSION_REQUEST_CODE);
        }
    }

    private void sendNotification_pic() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.icon_cat)
                        .setContentTitle("Кот загрустил")
                        .setContentText("Ты забыл обо мне?")
                        .setColor(Color.RED)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(contentIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                R.drawable.icon_cat)) // большая картинка
                        .addAction(R.drawable.hungrycat, "Запустить активность",
                                contentIntent)
                        // большая картинка из ресурсов
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(BitmapFactory.decodeResource(getResources(),
                                        R.drawable.hungrycat)))
                        .setAutoCancel(true); // автоматически закрыть уведомление после нажатия

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(MainActivity.this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {return;}
        notificationManager.notify(counter_pic++, builder.build());
    }
}
