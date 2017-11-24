package com.example.coco.snowedin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.coco.snowedin.view.SnowedInObject;
import com.example.coco.snowedin.view.SnowedInView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        SnowedInObject.Builder builder = new SnowedInObject.Builder(bitmap);
        SnowedInObject object = builder
                .setSpeed(7, true)
                .setSize(50, 50, true)
                .setWind(5, true, true)
                .build();
        SnowedInView fallingView = findViewById(R.id.mFall);
        fallingView.addFallingObject(object, 100);
    }
}
