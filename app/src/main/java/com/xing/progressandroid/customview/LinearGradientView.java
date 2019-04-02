package com.xing.progressandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.xing.progressandroid.utils.DensityUtil;

/**
 * 线性渐变
 */
public class LinearGradientView extends View {

    private Paint paint;
    private Paint linePaint;
    private LinearGradient linearGradient;
    private int mViewWidth;
    private int mViewHeight;
    private RectF rectF;

    public LinearGradientView(Context context) {
        super(context);
        init();
    }

    public LinearGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(DensityUtil.dp2px(getContext(), 2));
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(Color.BLACK);
        linePaint.setTextSize(DensityUtil.dp2px(getContext(), 14));
        linePaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;

        rectF = new RectF(0, 0, mViewWidth, mViewHeight);
        /**
         * 渐变范围为 左上角顶点 (0,0) -> 右上角顶点 (mViewWidth,0) ，
         * 超出这个范围以 Shader.TileMode.REPEAT 重复的形式填充， 颜色渐变的方向为 水平方向，
         *
         *
         * 如果渐变范围：  (0,0) -> (mViewWidth,mViewHeight), 则 渐变方向为 对角线方向
         */
        linearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW},
                new float[]{0.2f, 0.5f, 0.8f, 0.9f},
                Shader.TileMode.REPEAT);
        paint.setShader(linearGradient);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rectF, paint);
        for (int i = 0; i < 10; i++) {
            canvas.drawLine(rectF.width() * i * 0.1f, rectF.bottom, rectF.width() * i * 0.1f, rectF.bottom - 20, linePaint);
            canvas.drawText(String.valueOf(i * 0.1f), rectF.width() * i * 0.1f, rectF.bottom - 30, linePaint);
        }
    }
}
