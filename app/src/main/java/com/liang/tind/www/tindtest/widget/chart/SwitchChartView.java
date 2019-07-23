package com.liang.tind.www.tindtest.widget.chart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.liang.tind.www.tindtest.R;

import androidx.annotation.Nullable;

import static com.liang.tind.www.tindtest.widget.chart.ChartTypeDef.LEFT;
import static com.liang.tind.www.tindtest.widget.chart.ChartTypeDef.RIGHT;


/**
 * Created by Administrator on 2018/3/15.
 */

public class SwitchChartView extends View {

//    private int mRectWidth;
//    private int mRectHeight;

    private int mRadius;
    private int mSpace;
    private int mBackgroundColor;
    private int mTextColor;
    private int mTextSize;
    private int mButtonColor;

    private String mLeftText;
    private String mRightText;

    private RectF mBoundRectF = null;
    private RectF mButtonLeftRectF = null;
    private RectF mButtonRightRectF = null;

    private SwitchListener mSwitchListener = null;

    //按钮当前的位置
    private int mCurrentButtonAlign = LEFT;

    //当前显示的文字
    private String mCurrentText;

    //当前显示的按钮
    private RectF mCurrentButtonRectF;

//    //系数
//    private int mCoefficient;
//文字的Baseline
    private int mTextBaseline;

    private Paint mPaint;

    private Paint.FontMetricsInt mFontMetrics;

    public SwitchChartView(Context context) {
        this(context, null);
    }

    public SwitchChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchChartView);

        mRadius = typedArray.getDimensionPixelSize(R.styleable.SwitchChartView_rectRadius, context.getResources().getDimensionPixelSize(R.dimen.sports_kit_4dip));
        mSpace = typedArray.getDimensionPixelSize(R.styleable.SwitchChartView_buttonSpace, context.getResources().getDimensionPixelSize(R.dimen.sports_kit_2dip));
        mBackgroundColor = typedArray.getColor(R.styleable.SwitchChartView_backgroundColor, context.getResources().getColor(R.color.color7));
        mTextColor = typedArray.getColor(R.styleable.SwitchChartView_textColor, context.getResources().getColor(R.color.color3));
        mButtonColor = typedArray.getColor(R.styleable.SwitchChartView_buttonColor, context.getResources().getColor(R.color.color3));
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.SwitchChartView_textSize, context.getResources().getDimensionPixelSize(R.dimen.fontsize5));
        mLeftText = typedArray.getString(R.styleable.SwitchChartView_leftText);
        mCurrentText = mRightText = typedArray.getString(R.styleable.SwitchChartView_rightText);

        typedArray.recycle();

        initView(context);
    }

    private void initView(Context context) {
        if (TextUtils.isEmpty(mLeftText)){
            mLeftText = context.getString(R.string.sports_kit_month);
        }
        if (TextUtils.isEmpty(mRightText)){
            mRightText = context.getString(R.string.sports_kit_week);
        }
        mCurrentText = mRightText;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mFontMetrics = mPaint.getFontMetricsInt();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mRectWidth = MeasureSpec.getSize(widthMeasureSpec);
        int  mRectHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (mBoundRectF == null) {
            mBoundRectF = new RectF(0, 0, mRectWidth, mRectHeight);
        }

        if (mButtonLeftRectF == null) {
            mButtonLeftRectF = new RectF(mSpace, mSpace, mRectWidth / 2 , mRectHeight - mSpace);
        }

        if (mButtonRightRectF == null) {
            mButtonRightRectF = new RectF(mRectWidth / 2, mSpace, mRectWidth -mSpace , mRectHeight - mSpace);
        }

        mCurrentButtonRectF = mButtonLeftRectF;

        setMeasuredDimension(mRectWidth, mRectHeight);
    }
    public void setSwitchButtonAlign (@ChartTypeDef.ChartButtonAlign int align) {
        if (mCurrentButtonAlign == align) return;

        mCurrentButtonAlign = align;
        if (align == RIGHT) {
            mCurrentButtonRectF = mButtonRightRectF;
            mCurrentText = mLeftText;

        } else if (align == LEFT) {
            mCurrentButtonRectF = mButtonLeftRectF;
            mCurrentText = mRightText;

        }

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(mBackgroundColor);
        canvas.drawRoundRect(mBoundRectF, mRadius, mRadius, mPaint);

        canvas.save();

        mPaint.setColor(mButtonColor);
        canvas.drawRoundRect(mCurrentButtonRectF, mSpace, mSpace, mPaint);

        mPaint.setColor(mTextColor);
        mTextBaseline = (int) ((mBoundRectF.top + mBoundRectF.bottom - mFontMetrics.top - mFontMetrics.bottom) / 2);
        canvas.drawText(mCurrentText, mCurrentButtonRectF.centerX() - mCurrentButtonRectF.width() * mCurrentButtonAlign, mTextBaseline, mPaint);

        canvas.restore();
    }

    public void performClick(@ChartTypeDef.ChartAxisDivide int divide) {
        if (mSwitchListener != null) {
            mSwitchListener.onSwitchChanged(divide);
        }
        performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (mCurrentButtonAlign == LEFT){
                    setSwitchButtonAlign(RIGHT);
                    performClick(ChartTypeDef.MOUTH_DIVIDE);
                } else {
                    setSwitchButtonAlign(LEFT);
                    performClick(ChartTypeDef.WEEK_DIVIDE);
                }
                break;

        }
        return true;
    }
    public void onSetSwitchListener(SwitchListener switchListener){
        this.mSwitchListener = switchListener;

    }

    public interface SwitchListener {
        void onSwitchChanged(int divide);
    }
}
