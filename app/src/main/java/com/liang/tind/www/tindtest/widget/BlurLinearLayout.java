package com.liang.tind.www.tindtest.widget;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @author 梁天德
 * @date 2020/01/08 10:40
 * desc
 */
public class BlurLinearLayout extends LinearLayout {
    private static final String TAG = "BlurLinearLayout";
    private Paint mPaint;
    private BlurMaskFilter mMaskfilter;

    public BlurLinearLayout(Context context) {
        super(context);
        init();
    }

    public BlurLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BlurLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStrokeWidth(1);

//        mPaint.setColor(Color.RED);
        setWillNotDraw(false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMaskfilter = new BlurMaskFilter(h, BlurMaskFilter.Blur.NORMAL);
        mPaint.setMaskFilter(mMaskfilter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        canvas.drawRect(20, 20, getRight() - 20, getBottom() - 20, mPaint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow(): ");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow(): ");
    }
}
