package com.xing.progressandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

/**
 * 心形图片
 */
public class XfermodeHeartShapeImageView extends android.support.v7.widget.AppCompatImageView {

    private int mViewWidth;
    private int mViewHeight;
    private Paint paint;
    private PorterDuffXfermode xfermode;
    private float radius;
    private Path path;

    public XfermodeHeartShapeImageView(Context context) {
        this(context, null);
    }

    public XfermodeHeartShapeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        radius = Math.min(mViewWidth, mViewHeight) / 3f;

        // 获取绘制图片对应的 paint
        paint = ((BitmapDrawable) getDrawable()).getPaint();

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
        int saveCount = canvas.saveLayer(0, 0, mViewWidth, mViewHeight, null, Canvas.ALL_SAVE_FLAG);
        // 绘制 dst 心形
        /***为什么 paint.setStyle() 放在 onDraw() 中才生效，放在 onSizeChanged() 中进行不能生效***/
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);
        // 将绘制图片对应的 paint Xfermode 设置为 SRC_IN , 重叠的部分显示为 src 图片， dst 中不重叠的部分不变， src 中不重叠的部分显示为透明
        paint.setXfermode(xfermode);
        // 绘制 src 图片
        super.onDraw(canvas);
        paint.setXfermode(null);
        canvas.restoreToCount(saveCount);
    }
}
