package com.example.coco.snowedin.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.util.Random;

/**
 * Created by coco on 2017/11/24.
 */

public class FallingObject {
    private int initY;
    private int initX;
    private Random random;
    private int parentWidth;
    private int parentHeight;
    private float objectWidth;
    private float objectHeight;

    public int initSpeed;
    public int initWindLevel;

    public float presentX;
    public float presentY;
    public float presentSpeed;
    private float angle;

    private Bitmap bitmap;
    public Builder builder;

    private boolean isSpeedRandom;
    private boolean isSizeRandom;
    private boolean isWindRandom;
    private boolean isWindChange;

    private static final int defaultWindLevel = 10;
    private static final int defaultWindSpeed = 0;
    private static final int defaultSpeed = 10;

    private static final float HALF_PI = (float) (Math.PI / 2);

    public FallingObject(int parentWidth, int parentHeight, Builder builder) {
        random = new Random();
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        this.builder = builder;
        initX = random.nextInt(parentWidth);
        initY = random.nextInt(parentHeight) - parentHeight;
        presentX = initX;
        presentY = initY;


        initSpeed = builder.initSpeed;
        presentSpeed = initSpeed;
        bitmap = builder.bitmap;
        objectWidth = bitmap.getWidth();
        objectHeight = bitmap.getHeight();
    }

    private FallingObject(Builder builder) {
        this.builder = builder;
        initSpeed = builder.initSpeed;
        bitmap = builder.bitmap;
    }

    public static final class Builder {
        private int initSpeed;
        private int initWindLevel;
        private Bitmap bitmap;

        private boolean isSpeedRandom;
        private boolean isSizeRandom;
        private boolean isWindRandom;
        private boolean isWindChange;

        public Builder(Bitmap bitmap) {
            this.initSpeed = defaultSpeed;
            this.initWindLevel = defaultWindLevel;
            this.bitmap = bitmap;

            this.isSizeRandom = false;
            this.isSpeedRandom = false;
            this.isWindRandom = false;
            this.isWindChange = false;
        }

        public Builder setSpeed(int speed) {
            this.initSpeed = speed;
            return this;
        }

        public Builder setSpeed(int speed, boolean isRandomSpeed) {
            this.initSpeed = speed;
            this.isSpeedRandom = isRandomSpeed;
            return this;
        }

        public Builder setSize(int w, int h) {
            this.bitmap = changeBitmapSize(this.bitmap, w, h);
            return this;
        }

        public Builder setSize(int w, int h, boolean isRandomSize) {
            this.bitmap = changeBitmapSize(this.bitmap, w, h);
            this.isSizeRandom = isRandomSize;
            return this;
        }

        public Builder setWind(int level, boolean isWindRandom, boolean isWindChange) {
            this.initWindLevel = level;
            this.isWindRandom = isWindRandom;
            this.isWindChange = isWindChange;
            return this;
        }

        public FallingObject build() {
            return new FallingObject(this);
        }
    }

    public void drawObject(Canvas canvas) {
        moveObject();
        canvas.drawBitmap(bitmap, presentX, presentY, null);
    }

    private void moveObject() {
        moveX();
        moveY();
        if (presentY > parentHeight || presentX < -bitmap.getWidth() || presentX > parentWidth + bitmap.getWidth()) {
            reset();
        }

    }

    private void moveX() {
        presentX += defaultWindSpeed * Math.sin(angle);
        if (isWindChange) {
            angle += (random.nextBoolean() ? -1 : 1) * Math.random() * 0.0025;
        }
    }

    private void reset() {
        presentY = -objectHeight;
        randomSpeed();
        randomWind();
    }

    private void randomWind() {
        if (isWindRandom) {
            angle = (float) ((random.nextBoolean() ? -1 : 1) * Math.random() * initWindLevel / 50);
        } else {
            angle = initWindLevel / 50;
        }
        if (angle > HALF_PI) {
            angle = HALF_PI;
        } else if (angle < -HALF_PI) {
            angle = -HALF_PI;
        }
    }

    private void randomSpeed() {
        if (isSpeedRandom) {
            presentSpeed = (float) (((random.nextInt(3) + 1) * 0.1 + 1) * initSpeed);
        } else {
            presentSpeed = initSpeed;
        }
    }

    private void moveY() {
        presentY += presentSpeed;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private void randomSize() {
        if (isSizeRandom) {
            float r = (random.nextInt(10) + 1) * 0.1f;
            float rW = r * builder.bitmap.getWidth();
            float rH = r * builder.bitmap.getHeight();
            bitmap = changeBitmapSize(builder.bitmap, (int) rW, (int) rH);
        } else {
            bitmap = builder.bitmap;
        }
        objectWidth = bitmap.getWidth();
        objectHeight = bitmap.getHeight();
    }

    public static Bitmap changeBitmapSize(Bitmap bitmap, int newW, int newH) {
        int oldW = bitmap.getWidth();
        int oldH = bitmap.getHeight();
        float scaleWidth = ((float) newW) / oldW;
        float scaleHeight = ((float) newH) / oldH;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, oldW, oldH, matrix, true);
        return bitmap;
    }
}
