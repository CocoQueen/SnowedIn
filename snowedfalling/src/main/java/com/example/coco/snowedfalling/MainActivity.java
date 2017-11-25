package com.example.coco.snowedfalling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.dingmouren.fallingview.FallingView;

public class MainActivity extends AppCompatActivity {

    private FallingView mFallingView;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFallingView = findViewById(R.id.falling_view);
        img = findViewById(R.id.img);
        img.setImageResource(R.drawable.bg1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.img1:
                mFallingView.setImageResource(R.drawable.img1);
                img.setImageResource(R.drawable.bg2);
                mFallingView.setDelay(7);
                mFallingView.setScale(3);
                mFallingView.setDensity(80);
                break;
            case R.id.img2:
                mFallingView.setImageResource(R.drawable.img2);
                img.setImageResource(R.drawable.bg3);
                mFallingView.setDelay(10);
                mFallingView.setScale(3);
                mFallingView.setDensity(80);
                break;
            case R.id.img3:
                mFallingView.setImageResource(R.drawable.img3);
//                img.setImageResource(0);
//                img.setBackgroundColor(Color.parseColor("#ff6f00"));
                img.setImageResource(R.drawable.bg2);
                mFallingView.setDelay(7);
                mFallingView.setScale(3);
                mFallingView.setDensity(80);
                break;
            case R.id.img4:
                mFallingView.setImageResource(R.drawable.img4);
                img.setImageResource(R.drawable.bg4);
                mFallingView.setDelay(10);
                mFallingView.setScale(3);
                mFallingView.setDensity(80);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
