package com.liang.tind.www.tindtest.widget.chart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.liang.tind.www.tindtest.R;


/**
 * Created by Administrator on 2018/3/15.
 */

public class GraphChartView extends View implements ChartTypeDef {
    private final int DEFAULT_MAX_VALUE = 500;
    private final int INVALID_VALUE = -100;

    private int mScreenWidth = INVALID_VALUE;
    private int mCircleRadius;
    private int mCircleColor;
    private int mLineSize;
    private int mLineColor;
    private int mTextSize;
    private int mTextColor;
    private int mGradientFromColor;
    private int mGradientToColor;
//    private int mGradientHeight;
    private int mMarginTop;
    //    几等分
    private int mDivide = WEEK_DIVIDE;

    //    最大值
    private long mMaxValue = DEFAULT_MAX_VALUE;
//    private List<StepChartData> mListData = new ArrayList<>();
    private Paint mPaint;

    private LinearGradient mLinearGradient;
    private int mPadding;
    private float[] mXPoints = null ;
    private float[] mYPoints = null;
    private String[] mDateTexts = null;
    private DashPathEffect mDashPathEffect;
    private int mDashedColor;

    private int mYAxisHeight;
    private int mLineTextPadding;

    private Path mDashPath;

    private int mDashSize;
    private int mAxisSize;
    private int mDotHeight;
    private int mMarginBottom;

    private Path mDrawPath;

    public GraphChartView(Context context) {
        this(context, null);
    }

    public GraphChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GraphChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GraphChartView);

        mCircleRadius = typedArray.getDimensionPixelSize(R.styleable.GraphChartView_circleRadius, context.getResources().getDimensionPixelSize(R.dimen.sports_kit_3dip));
        mCircleColor = typedArray.getColor(R.styleable.GraphChartView_circleColor, context.getResources().getColor(R.color.sports_kit_white1));

        mLineSize = typedArray.getDimensionPixelSize(R.styleable.GraphChartView_lineSize, context.getResources().getDimensionPixelSize(R.dimen.sports_kit_2dip));
        mLineColor = typedArray.getColor(R.styleable.GraphChartView_lineColor, context.getResources().getColor(R.color.sports_kit_white1));

        mTextSize = typedArray.getDimensionPixelSize(R.styleable.GraphChartView_graphTextSize, context.getResources().getDimensionPixelSize(R.dimen.fontsize6));
        mTextColor = typedArray.getColor(R.styleable.GraphChartView_graphTextColor, context.getResources().getColor(R.color.color7));

        mPadding = typedArray.getDimensionPixelSize(R.styleable.GraphChartView_y_padding, context.getResources().getDimensionPixelSize(R.dimen.sports_kit_15dip));

        mGradientFromColor = typedArray.getColor(R.styleable.GraphChartView_gradientFromColor, context.getResources().getColor(R.color.sports_kit_gradient_from));
        mGradientToColor = typedArray.getColor(R.styleable.GraphChartView_gradientToColor, context.getResources().getColor(R.color.sports_kit_gradient_to));

        mMarginTop = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_20dip);

        mLineTextPadding = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_6dip);

        mDashSize = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_05dip);

        mAxisSize = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_1dip);

        mDotHeight = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_5dip);

        mDashedColor = context.getResources().getColor(R.color.sports_kit_dashed);
//        mGradientHeight = typedArray.getDimensionPixelSize(R.styleable.GraphChartView_gradientHeight, context.getResources().getDimensionPixelSize(R.dimen.challenge_route_173dip));

        mMarginBottom = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_50dip);

        typedArray.recycle();

        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mDashPathEffect = new DashPathEffect(new float[]{10, 10}, 0);

        mDashPath = new Path();
        mDashPath.moveTo(mPadding*2,mMarginTop - 10);

        mDrawPath = new Path();
    }

    public void updateGraphChart(@ChartTypeDef.ChartAxisDivide int divide, long maxValue, @NonNull float[] xPoints, @NonNull float[] yPoints, @NonNull String[] texts) {
        if (xPoints.length == 0
                || xPoints.length != yPoints.length || xPoints.length != texts.length) return;

        this.mDivide = divide;
        this.mMaxValue = maxValue;
        this.mXPoints = xPoints;
        this.mYPoints = yPoints;
        this.mDateTexts = texts;

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mScreenWidth == INVALID_VALUE) {
            mScreenWidth = getWidth();
            mLinearGradient = new LinearGradient(mPadding * 2, mMarginTop, 0, mYAxisHeight,
                    mGradientFromColor, mGradientToColor, Shader.TileMode.CLAMP);
            mDashPath.lineTo(mScreenWidth - mPadding*2,mMarginTop - 10);
        }

        if (mXPoints == null|| mXPoints.length != mYPoints.length || mXPoints.length != mDateTexts.length) return;

        mPaint.reset();
//        设置图形阴影
        mPaint.setShader(mLinearGradient);

//        Path path = new Path();
//        path.moveTo(mXPoints[0], mYAxisHeight);
//        for (int i = 0; i <= mDivide; i++) {
//            path.lineTo(mXPoints[i], mYPoints[i]);
//        }
//        path.lineTo(mXPoints[mDivide], mYAxisHeight);
//        path.close();
//        canvas.drawPath(path, mPaint);

        mDrawPath.reset();
        mDrawPath.moveTo(mXPoints[0], mYAxisHeight);
        for (int i = 0; i <= mDivide; i++) {
            mDrawPath.lineTo(mXPoints[i], mYPoints[i]);
        }
        mDrawPath.lineTo(mXPoints[mDivide], mYAxisHeight);
        mDrawPath.close();
        canvas.drawPath(mDrawPath, mPaint);

        mPaint.reset();

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTextColor);
        mPaint.setStrokeWidth(mAxisSize);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);

//        设置y轴坐标
        canvas.drawLine(mPadding * 2, mMarginTop - 10, mPadding * 2, mYAxisHeight, mPaint);
//        设置x轴坐标
        canvas.drawLine(mPadding, mYAxisHeight, mScreenWidth - mPadding, mYAxisHeight, mPaint);

        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        int center = (fontMetricsInt.top +  fontMetricsInt.bottom)/2;
        int baseline = mYAxisHeight + mLineTextPadding + mTextSize/2 - center ;
        //设置x轴上的坐标和文字
        for (int i = 0; i <= mDivide; i++) {
            canvas.drawLine(mXPoints[i], mYAxisHeight, mXPoints[i], mYAxisHeight - mDotHeight, mPaint);
            if (mDivide != MOUTH_DIVIDE || i % 5 == 0) {
                canvas.drawText(mDateTexts[i], mXPoints[i], baseline, mPaint);
            }
        }

        if (mDivide % 5 != 0) {
            canvas.drawText(mDateTexts[mDivide], mXPoints[mDivide], baseline, mPaint);
        }

//        设置最大值文字
        canvas.drawText(String.valueOf(mMaxValue),  mPadding ,mMarginTop ,mPaint);

        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(mLineSize);
//        描点和画直线
        canvas.drawCircle(mXPoints[0],mYPoints[0],mCircleRadius,mPaint);
        for (int i = 1; i <= mDivide; i++) {
            canvas.drawLine(mXPoints[i - 1], mYPoints[i - 1], mXPoints[i],mYPoints[i], mPaint);
            canvas.drawCircle(mXPoints[i],mYPoints[i],mCircleRadius,mPaint);
        }

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mDashedColor);
        mPaint.setStrokeWidth(mDashSize);
        mPaint.setPathEffect(mDashPathEffect);
//        设置虚线
        canvas.drawPath(mDashPath,mPaint);
        mPaint.reset();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        mYAxisHeight = height - mMarginBottom + mDotHeight;
        setMeasuredDimension(width,height);
    }
}
