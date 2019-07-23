package com.liang.tind.www.tindtest.widget.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.liang.tind.www.tindtest.R;

import androidx.annotation.Nullable;


/**
 * Created by Administrator on 2018/3/15.
 */

public class LabelChartView extends View {
    private final int INVALID_VALUE = -100;
    private float mLabelX = INVALID_VALUE;
    private float mLabelY = INVALID_VALUE;
    private Paint mPaint;
    private int mRectFRadius;
    private int mRectFColor;
    private int mTextSize;
    private int mTextColor;
    private int mPadding;
    private int mMarginTop;
    private int mLabelHeight;
    private String mLabel;
    private Paint.FontMetricsInt mFontMetricsInt;

    public LabelChartView(Context context) {
        this(context, null);
    }

    public LabelChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabelChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mRectFRadius = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_2dip);
        mRectFColor = context.getResources().getColor(R.color.color7);

        mTextSize = context.getResources().getDimensionPixelSize(R.dimen.fontsize8);
        mTextColor = context.getResources().getColor(R.color.color3);

        mPadding = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_4dip);

        mMarginTop = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_5dip);

        mLabelHeight = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_12dip);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mTextSize);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setTextAlign(Paint.Align.CENTER);

        mFontMetricsInt = mPaint.getFontMetricsInt();
    }

    public void setLabelXY(float x, float y, String label) {
        this.mLabelX = x;
        this.mLabelY = y;
        this.mLabel = label;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (TextUtils.isEmpty(mLabel)) return;

        float textWith = mPaint.measureText(mLabel);
        float width = textWith + mPadding * 2;
        float center = mLabelY - mMarginTop - mLabelHeight / 2;
        float baseline = center - (mFontMetricsInt.top + mFontMetricsInt.bottom) / 2;

        canvas.save();

        canvas.translate(mLabelX, center);
        mPaint.setColor(mRectFColor);
        canvas.drawRoundRect(new RectF(-width / 2, -mLabelHeight / 2, width / 2, mLabelHeight / 2), mRectFRadius, mRectFRadius, mPaint);
        mPaint.setColor(mTextColor);
        canvas.drawText(mLabel, 0, baseline - center, mPaint);

        canvas.restore();
    }
}
