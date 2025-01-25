package com.example.ma_ex_10;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private AssetManager assetManager;

    private int catSound, chickenSound, cowSound, dogSound, duckSound, sheepSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView catImage = findViewById(R.id.image_cat);
        ImageView chickenImage = findViewById(R.id.image_chicken);
        ImageView cowImage = findViewById(R.id.image_cow);
        ImageView dogImage = findViewById(R.id.image_dog);
        ImageView duckImage = findViewById(R.id.image_duck);
        ImageView sheepImage = findViewById(R.id.image_sheep);

        // Создание SoundPool с параметрами
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();

        // Получаем доступ к менеджеру активов
        assetManager = getAssets();

        // Загружаем звуки
        catSound = loadSound("cat.ogg");
        chickenSound = loadSound("chicken.ogg");
        cowSound = loadSound("cow.ogg");
        dogSound = loadSound("dog.ogg");
        duckSound = loadSound("duck.ogg");
        sheepSound = loadSound("sheep.ogg");

        // Устанавливаем обработчики кликов
        catImage.setOnClickListener(v -> playSound(catSound));
        chickenImage.setOnClickListener(v -> playSound(chickenSound));
        cowImage.setOnClickListener(v -> playSound(cowSound));
        dogImage.setOnClickListener(v -> playSound(dogSound));
        duckImage.setOnClickListener(v -> playSound(duckSound));
        sheepImage.setOnClickListener(v -> playSound(sheepSound));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (soundPool != null) {
            soundPool.release();
        }
    }

    // Метод для воспроизведения звука
    private void playSound(int sound) {
        if (sound > 0) {
            soundPool.play(sound, 1F, 1F, 1, 0, 1F);
        }
    }

    // Метод для загрузки звуков
    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = assetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Meow", "Не могу загрузить файл " + fileName);
            Toast.makeText(this, "Ошибка загрузки файла " + fileName, Toast.LENGTH_SHORT).show();
            return -1;
        }
        return soundPool.load(afd, 1);
    }
}

