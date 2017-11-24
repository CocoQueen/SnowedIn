package com.example.coco.snowedin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.coco.snowedin.view.FallingObject;
import com.example.coco.snowedin.view.FallingView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        FallingObject.Builder builder = new FallingObject.Builder(bitmap);
        FallingObject object = builder
                .setSpeed(7, true)
                .setSize(50, 50, true)
                .setWind(5, true, true)
                .build();
        FallingView fallingView = findViewById(R.id.mFall);
        fallingView.addFallingObject(object, 100);
    }
}
