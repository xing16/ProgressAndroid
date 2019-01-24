package com.xing.progressandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * 径向渐变着色器
 */
public class RadialGradientView extends View {
    private Paint paint;
    private int mViewWidth;
    private int mViewHeight;
    private RectF rectF;

    public RadialGradientView(Context context) {
        super(context);
        init();
    }

    public RadialGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;

        rectF = new RectF(0, 0, mViewWidth, mViewHeight);


        /**
         *  径向着色，中心点为 view 的 中心点，半径为 100px, 超过这个范围的区域以 repeat 重复的形式填充
         */
        RadialGradient radialGradient = new RadialGradient(mViewWidth / 2,
                mViewHeight / 2, 100,
                Color.parseColor("#ff0000"),
                Color.parseColor("#0000ff"),
                Shader.TileMode.REPEAT);

        paint.setShader(radialGradient);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rectF, paint);
    }

}
