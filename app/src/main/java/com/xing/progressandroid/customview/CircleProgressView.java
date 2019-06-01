package com.xing.progressandroid.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class CircleProgressView extends View {

    private int width;
    private int height;
    private Paint paint;
    private float radius;
    private int centerX;
    private int centerY;
    private int gap = dp2px(5);
    private float circleRadius;
    private Paint progressPaint;
    private float angle;
    private RectF rectF;
    private int progressPaintStrokeWidth;


    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(dp2px(3));

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaintStrokeWidth = dp2px(5);
        progressPaint.setStrokeWidth(progressPaintStrokeWidth);
        progressPaint.setColor(Color.BLUE);
        progressPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        radius = Math.min(width, height) / 2f - progressPaintStrokeWidth / 2f;
        rectF = new RectF(-radius, -radius,
                radius, radius);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(width / 2f, height / 2f);
        // 绘制外圈圆
        progressPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF, -90, angle, false, progressPaint);
        progressPaint.setColor(Color.WHITE);
        canvas.drawArc(rectF, angle - 90, 360 - angle, false, progressPaint);

//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(0, 0, radius, paint);

        // 绘制内部圆
        canvas.drawCircle(0, 0, circleRadius, paint);

        canvas.restore();
    }

    private void progressAnimation() {
        ValueAnimator progressAnimator = ValueAnimator.ofFloat(0, 360);
        progressAnimator.setDuration(3000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                angle = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        progressAnimator.start();
    }

    private boolean isScale = true;

    private void circleAnimation() {
        ValueAnimator progressAnimator = ValueAnimator.ofFloat(isScale ? radius - gap : 0, isScale ? 0 : radius - gap);
        progressAnimator.setDuration(1000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                circleRadius = (float) animation.getAnimatedValue();
                Log.e("debug", "onAnimationUpdate: " + circleRadius);
                if (!isScale) {
                    paint.setColor(Color.BLUE);
                }
                invalidate();
            }


        });
        progressAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isScale = false;
                circleAnimation();
            }
        });
        progressAnimator.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                circleAnimation();
                progressAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(event);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
