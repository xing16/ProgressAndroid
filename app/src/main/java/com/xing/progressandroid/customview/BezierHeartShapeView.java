package com.xing.progressandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class BezierHeartShapeView extends View {

    private Path path;
    private int mViewWidth;
    private int mViewHeight;
    private Paint paint;

    public BezierHeartShapeView(Context context) {
        this(context, null);
    }

    public BezierHeartShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mViewWidth = w;
        mViewHeight = h;

        // 二阶贝塞尔曲线
        path.moveTo(mViewWidth / 2f, mViewHeight / 3f);
        path.cubicTo(mViewWidth / 7f, mViewHeight / 9f,
                mViewWidth / 13f, (mViewHeight * 2) / 5f,
                mViewWidth / 2f, (mViewHeight * 4) / 5f);
        path.cubicTo(
                mViewWidth * 12 / 13f, (mViewHeight * 2) / 5f,
                mViewWidth * 6 / 7f, mViewHeight / 9f,
                mViewWidth / 2f, mViewHeight / 3f);
        path.close();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制 dst 心形
        canvas.drawPath(path, paint);
    }
}
