package com.xing.progressandroid.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class SweepGradientView extends View {

    private static final String TAG = "SweepGradientView";
    private Paint paint;
    private int mViewWidth;
    private int mViewHeight;
    private float centerX;
    private float centerY;
    private float radius;
    private int degree = -90;

    public SweepGradientView(Context context) {
        super(context);
        init();
    }

    public SweepGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged: ");
        mViewWidth = w;
        mViewHeight = h;

        centerX = mViewWidth / 2f;
        centerY = mViewHeight / 2f;

        radius = Math.min(mViewWidth, mViewHeight) / 2f;

        /**
         *  扇形，绕着一个中心点进行扫描的渐变着色器。
         *  中心点为 ( mViewWidth / 2, mViewHeight / 2 ) , 顺时针 0 度开始渐变
         */
        SweepGradient sweepGradient = new SweepGradient(centerX, centerY,
                Color.parseColor("#FFB9B3B3"),
                Color.parseColor("#ffcc1111"));

        paint.setShader(sweepGradient);

        startAnimator();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.rotate(degree, centerX, centerY);
        canvas.drawCircle(centerX, centerY, radius, paint);
        canvas.restore();
    }

    private void startAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(-90, 270);
        valueAnimator.setDuration(3000);
        // 动画重复次数
        valueAnimator.setRepeatCount(100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degree = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
    }
}
