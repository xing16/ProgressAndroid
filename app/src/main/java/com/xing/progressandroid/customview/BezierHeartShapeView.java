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
        // 二阶贝塞尔曲线
        path.moveTo(mViewWidth / 2f, mViewHeight / 4f);
        path.cubicTo(mViewWidth / 10f, mViewHeight / 12f,
                mViewWidth / 9f, (mViewHeight * 3) / 5f,
                mViewWidth / 2f, (mViewHeight * 5) / 6f);
        path.cubicTo(
                mViewWidth * 8 / 9f, (mViewHeight * 3) / 5f,
                mViewWidth * 9 / 10f, mViewHeight / 12f,
                mViewWidth / 2f, mViewHeight / 4f);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制 dst 心形
        canvas.drawPath(path, paint);
    }
}
