package com.xing.progressandroid.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.xing.progressandroid.R;

public class XfermodeCircleImageView extends android.support.v7.widget.AppCompatImageView {

    private int mViewWidth;
    private int mViewHeight;
    private Paint paint;
    private PorterDuffXfermode xfermode;
    private RectF recF;
    private float radius;

    public XfermodeCircleImageView(Context context) {
        this(context, null);
    }

    public XfermodeCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;

        radius = Math.min(mViewWidth, mViewHeight) / 2f;
//        paint = ((BitmapDrawable) getDrawable()).getPaint();
        recF = new RectF(0, 0, mViewWidth, mViewHeight);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saveLayer = canvas.saveLayer(0, 0, mViewWidth, mViewHeight, paint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(createMask(), 0, 0, paint);
        paint.setXfermode(xfermode);
        canvas.drawCircle(mViewWidth >> 1, mViewHeight >> 1, radius, paint);
        // 绘制边框
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.orange));
        canvas.drawCircle(mViewWidth >> 1, mViewHeight >> 1, radius , paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }

    /**
     * 创建一个和图片相同大小的遮罩层(背景为 ImageView 背景色)，覆盖在图片上，
     * 然后在遮罩层上绘制一个圆形，绘制时设置为 SRC_OUT，这样圆形就扣掉了，显示出了图片，
     * 没有被扣掉的部分显示为遮罩层的背景颜色。
     *
     * @return
     */
    private Bitmap createMask() {
        //创建跟imageview相同宽高的遮罩层
        Bitmap bitmapMask = Bitmap.createBitmap(mViewWidth, mViewHeight, Bitmap.Config.ALPHA_8);
        Canvas canvasMask = new Canvas(bitmapMask);
        //遮罩层颜色
        int maskColor;
        Drawable background = getBackground();
        if (background instanceof ColorDrawable) {
            maskColor = ((ColorDrawable) background).getColor();
        } else {
            maskColor = Color.WHITE;
        }
        canvasMask.drawColor(maskColor);
        return bitmapMask;
    }
}
