package com.xing.progressandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

import com.xing.progressandroid.R;

public class XfermodeCircleImageView2 extends android.support.v7.widget.AppCompatImageView {

    private int mViewWidth;
    private int mViewHeight;
    private Paint paint;
    private PorterDuffXfermode xfermode;
    private RectF recF;
    private float radius;

    public XfermodeCircleImageView2(Context context) {
        this(context, null);
    }

    public XfermodeCircleImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;

        radius = Math.min(mViewWidth, mViewHeight) / 2f;
        paint = ((BitmapDrawable) getDrawable()).getPaint();
        paint.setAntiAlias(true);
        recF = new RectF(0, 0, mViewWidth, mViewHeight);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }


    /**
     * 先绘制 dst 圆形，
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        int saveCount = canvas.saveLayer(0, 0, mViewWidth, mViewHeight, null, Canvas.ALL_SAVE_FLAG);
        // 绘制 dst 圆形
        canvas.drawCircle(mViewWidth / 2f, mViewHeight / 2f, radius, paint);
        // Xfermode 设置为 SRC_IN , 重叠的部分显示为 src 图片， dst 中不重叠的部分不变， src 中不重叠的部分显示为透明
        paint.setXfermode(xfermode);
        // 绘制 src 图片
        super.onDraw(canvas);
        // 绘制  src 边框
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        paint.setStrokeWidth(10);
        canvas.drawCircle(mViewWidth / 2f, mViewHeight / 2f, radius - paint.getStrokeWidth() / 2f, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saveCount);
    }
}
