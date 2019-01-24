package com.xing.progressandroid.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import com.xing.progressandroid.R;
import com.xing.progressandroid.utils.DensityUtil;

/**
 * 高亮进度 ImageView
 */
public class HighlightProgressImageView extends AppCompatImageView {

    private Paint backgroundPaint;
    private Paint circlePaint;
    private int radius;
    private int width;
    private int height;
    private int roundCorner;
    private Path clipPath;
    private RectF pathRectF;
    private RectF circleRectF;
    private RectF backgroundRectF;
    private PorterDuffXfermode porterDuffXfermode;
    private AnimatorSet animatorSet;
    private ValueAnimator angleAnimator;
    private ValueAnimator scaleAnimator;
    // 扇形角度
    private int angle;
    // 缩放半径
    private float scaleRadius = radius;
    private boolean needDrawArc = true;


    public HighlightProgressImageView(Context context) {
        this(context, null);
    }

    public HighlightProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(getResources().getColor(R.color.translucentGray));
        backgroundPaint.setStyle(Paint.Style.FILL);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(getResources().getColor(android.R.color.white));
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(DensityUtil.dp2Px(getContext(), 8));

//        setLayerType(View., null);
        radius = DensityUtil.dp2Px(getContext(), 40);
        roundCorner = DensityUtil.dp2Px(getContext(), 10);

        clipPath = new Path();
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        pathRectF = new RectF(0, 0, width, height);
        clipPath.addRoundRect(pathRectF, roundCorner, roundCorner, Path.Direction.CCW);
        circleRectF = new RectF(-radius, -radius, radius, radius);
        backgroundRectF = new RectF(-width / 2, -height / 2, width / 2f, height / 2f);
    }

    /**
     * 绘制步骤: 先绘制 圆， 再在圆上绘制灰色背景，绘制灰色背景时，将 Xfermode 设置为 PorterDuff.Mode.SRC_OUT， 这样重叠的部分就会变为透明，显示出正常的图片
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // 通过 path, 裁剪 canvas 画布
        canvas.clipPath(clipPath);
        // 绘制图片
        super.onDraw(canvas);
        //将绘制操作保存到新的图层，因为图像合成是很昂贵的操作，将用到硬件加速，这里将图像合成的处理放到离屏缓存中进行
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), backgroundPaint, Canvas.ALL_SAVE_FLAG);
        canvas.translate(width / 2f, height / 2f);
//        if (needDrawArc) {
        // 绘制 dst 圆环
        circlePaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, radius, circlePaint);
        // 绘制 dst 扇形
        circlePaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(circleRectF, -90, angle, true, circlePaint);
//        }
        circlePaint.setStyle(Paint.Style.FILL);
        // 绘制 dst 圆
        canvas.drawCircle(0, 0, scaleRadius, circlePaint);
        // 设置 Xfermode 为 SRC_OUT
        backgroundPaint.setXfermode(porterDuffXfermode);
        // 绘制 src 图片上层的灰色蒙层
        canvas.drawRoundRect(backgroundRectF, roundCorner, roundCorner, backgroundPaint);
        backgroundPaint.setXfermode(null);
        canvas.restoreToCount(saveCount);
    }

    /**
     * 开启动画
     */
    public void start() {
        startAnimator();
    }

    /**
     * 停止动画
     */
    public void stop() {
        if (animatorSet != null) {
            animatorSet.cancel();
            animatorSet = null;
        }
    }

    private void startAnimator() {
        // 扇形进度动画
        if (angleAnimator == null) {
            angleAnimator = ValueAnimator.ofInt(0, 360);
//            angleAnimator.setDuration(2000);
            angleAnimator.setInterpolator(new LinearInterpolator());
            angleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    angle = (int) animation.getAnimatedValue();
                    invalidate();
                }
            });
            angleAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    needDrawArc = false;
                }
            });
        }

        if (scaleAnimator == null) {
            scaleAnimator = ValueAnimator.ofFloat(radius, width > height ? width : height);
//            scaleAnimator.setDuration(2000);
            scaleAnimator.setInterpolator(new LinearInterpolator());
            scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
        }
        if (animatorSet == null) {
            animatorSet = new AnimatorSet();
            animatorSet.setDuration(2000);
            animatorSet.setInterpolator(new LinearInterpolator());
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }
            });
            animatorSet.playSequentially(angleAnimator, scaleAnimator);
        }
        animatorSet.start();
    }
}
