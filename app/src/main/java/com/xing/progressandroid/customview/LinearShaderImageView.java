package com.xing.progressandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class LinearShaderImageView extends AppCompatImageView {

    private int mViewWidth;
    private int mViewHeight;
    private LinearGradient linearGradient;
    private Matrix gradientMatrix;
    private int offset;
    private Paint paint;
    private RectF rectF;

    public LinearShaderImageView(Context context) {
        this(context, null);
    }

    public LinearShaderImageView(Context context, AttributeSet attrs) {
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

        rectF = new RectF(0, 0, mViewWidth, mViewHeight);
        paint = ((BitmapDrawable) getDrawable()).getPaint();

        // 线性渐变渲染器
        linearGradient = new LinearGradient(0, 0,
                mViewWidth, mViewHeight,
                new int[]{0x33000000, 0xffffffff, 0xffffffff, 0x33000000},
                new float[]{0, 0.45f, 0.55f, 1},
                Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        // 创建矩阵
        gradientMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制矩形
        canvas.drawRect(rectF, paint);

//        if (linearGradient != null) {
//            offset += mViewWidth / 5;
//            if (offset > 2 * mViewWidth) {
//                offset = -mViewWidth;
//            }
//            gradientMatrix.setTranslate(offset, 0);
//            // 为着色器设置矩阵
//            linearGradient.setLocalMatrix(gradientMatrix);
//            // 循环等待时间
//            postInvalidateDelayed(100);
//        }
    }
}
