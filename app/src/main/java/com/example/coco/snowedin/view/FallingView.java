package com.example.coco.snowedin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coco on 2017/11/24.
 */

public class FallingView extends View {
    private Context mContext;
    private AttributeSet mAttrs;

    private List<FallingObject>list;

    private int viewWidth;
    private int viewHeight;

    private static final int defaultWidth = 600;
    private static final int defaultHeight = 1000;
    private static final int reTime = 5;

//    private Paint mPaint;
//    private int snowY;


    public FallingView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public FallingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mAttrs = attrs;
        init();
    }

    private void init() {
       list=new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = measureSize(defaultHeight, heightMeasureSpec);
        int width = measureSize(defaultWidth, widthMeasureSpec);
        setMeasuredDimension(width, height);
        viewHeight = height;
        viewWidth = width;

    }

    private int measureSize(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            result = Math.min(result, size);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       if (list.size()>0){
           for (int i = 0; i < list.size(); i++) {
               list.get(i).drawObject(canvas);
           }
           getHandler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   invalidate();
               }
           }, reTime);
       }
    }
    public void addFallingObject(final FallingObject fallingObject, final int num){
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                for (int i = 0; i < num; i++) {
                    FallingObject newObject =new FallingObject(viewWidth,viewHeight,fallingObject.builder);
                    list.add(newObject);
                }
                invalidate();
                return true;
            }
        });
    }
}
