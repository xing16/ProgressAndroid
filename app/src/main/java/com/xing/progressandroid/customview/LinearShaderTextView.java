package com.xing.progressandroid.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

public class LinearShaderTextView extends AppCompatTextView {

    private static final String TAG = "LinearShaderTextView";
    private int mViewWidth;
    private int mViewHeight;
    private LinearGradient linearGradient;
    private Matrix gradientMatrix;
    private float value;

    public LinearShaderTextView(Context context) {
        this(context, null);
    }

    public LinearShaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;

        value = -mViewWidth;
        // 获取当前绘制 TextView 的 画笔对象
        Paint paint = getPaint();

        int paintColor = paint.getColor();
        Log.e(TAG, "onSizeChanged: paintColor = " + paintColor);
        int currentTextColor = getCurrentTextColor();
        Log.e(TAG, "onSizeChanged: currentTextColor = " + currentTextColor);

        // 自定义渐变渲染器
        linearGradient = new LinearGradient(0, 0,
                mViewWidth, mViewHeight,
                new int[]{currentTextColor, Color.WHITE, currentTextColor},
                new float[]{0.25f, 0.5f, 0.75f},
                Shader.TileMode.CLAMP);

        // 为画笔设置渐变渲染器
        paint.setShader(linearGradient);
        // 创建矩阵
        gradientMatrix = new Matrix();

        startAnimator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (linearGradient != null) {
            gradientMatrix.setTranslate(value, 0);
            // 为着色器设置矩阵
            linearGradient.setLocalMatrix(gradientMatrix);
        }
    }

    public void startAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(-mViewWidth, mViewWidth);
        valueAnimator.setDuration(1500);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }
}
