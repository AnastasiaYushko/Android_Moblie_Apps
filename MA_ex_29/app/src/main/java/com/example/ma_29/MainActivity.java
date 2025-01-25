package com.example.ma_29;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("5d425631-ef8a-49da-8aa2-13ff7f641303");
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapview);
        ImageProvider imageProvider = ImageProvider.fromResource(this, R.drawable.ic_pin);

        PlacemarkMapObject placemark1 = mapView.getMap().getMapObjects().addPlacemark(new Point(55.0308495386, 73.269687073));
        placemark1.setIcon(imageProvider);
        placemark1.setText("Это 4 корпус");

        PlacemarkMapObject placemark2 = mapView.getMap().getMapObjects().addPlacemark(new Point(55.02843, 73.26204));
        placemark2.setIcon(imageProvider);
        placemark2.setText("Это 1 корпус");

        CameraPosition cameraPosition = new CameraPosition(
                new Point(55.03084, 73.26590), // координаты
                15.0f, // уровень масштабирования
                15.0f, // азимут
                3.0f // наклон
        );

        // Перемещение камеры на новую позицию
        mapView.getMap().move(cameraPosition);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }
}