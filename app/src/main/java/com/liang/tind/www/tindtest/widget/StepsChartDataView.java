package com.liang.tind.www.tindtest.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.bean.StepChartData;
import com.liang.tind.www.tindtest.util.CommonUtils;
import com.liang.tind.www.tindtest.util.DateUtil;
import com.liang.tind.www.tindtest.widget.chart.ChartTypeDef;
import com.liang.tind.www.tindtest.widget.chart.GraphChartView;
import com.liang.tind.www.tindtest.widget.chart.LabelChartView;
import com.liang.tind.www.tindtest.widget.chart.SwitchChartView;
import com.maxwell.imkid.library.tool.ToastUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/3/27.
 */

public class StepsChartDataView extends ViewGroup implements ChartTypeDef {

    private final static int DEFAULT_MAX_VALUE = 500;

//    private final static int DEFAULT_SLIDE_OFFSET = 100;

    public static final int DEFAULT_SNAP_VELOCITY = 600;

    private SwitchChartView mSwitchChartView;
    private GraphChartView mGraphChartView;
    private LabelChartView mLabelChartView;

    private int mRangeTop;
    private int mRangeLeft;
    private int mRangeRight;
    private int mRangeBottom;

    private int mLeftMargin;
    private int mTopMargin;

    private int mChartPaddingTop;
    private int mChartPaddingBottom;

    private int mSwitchHeight;
    private int mSwitchWidth;

    //    间距
    private float mInterval;

    //    缩放比
    private float mScale;

    private int mDivide = WEEK_DIVIDE;

    private long mMaxValue = DEFAULT_MAX_VALUE;

    private float[] mXPoints = new float[MOUTH_DIVIDE + 1];
    private float[] mYPoints = new float[MOUTH_DIVIDE + 1];

    private String[] mDateTexts = new String[MOUTH_DIVIDE + 1];

    private int mGraphHeight;

    private int mPosition = 0;

    private int mYAxisHeight;

    private int mScreenWidth;

    private int mCurrentPage = 1;

    private String mBorderYearMonth;

    private Set<String> mStepKeySet = new HashSet();
    private List<StepChartData> mListStepData = new ArrayList();

    private ChartDataListener mListener;

    private Paint mPaint;

    private Paint mDebugPaint;

    protected VelocityTracker mVelocityTracker;

    public StepsChartDataView(Context context) {
        this(context, null);
    }

    public StepsChartDataView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepsChartDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mDebugPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDebugPaint.setStyle(Paint.Style.STROKE);
        mDebugPaint.setStrokeWidth(1);
        mDebugPaint.setColor(Color.RED);

        mLeftMargin = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_15dip);
        mTopMargin = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_11dip);

        mSwitchWidth = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_56dip);
        mSwitchHeight = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_20dip);

        mRangeLeft = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_30dip);
        mRangeTop = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_45dip);
        mRangeRight = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_15dip);

        mChartPaddingTop = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_8dip);
        mChartPaddingBottom = context.getResources().getDimensionPixelSize(R.dimen.sports_kit_50dip);

        mScreenWidth = CommonUtils.getScreenSize(context).x;

        // 切换开关
        mSwitchChartView = new SwitchChartView(context, attrs);
        addView(mSwitchChartView);

        // 走势图
        mGraphChartView = new GraphChartView(context, attrs);
        addView(mGraphChartView);

        // 线上的提示
        mLabelChartView = new LabelChartView(context, attrs);
        addView(mLabelChartView);

        initEvent();
    }

    private void initEvent() {
        mSwitchChartView.onSetSwitchListener(new SwitchChartView.SwitchListener() {
            @Override
            public void onSwitchChanged(int divide) {
                mDivide = divide;
                mPosition = 0;
                mIsStopLoadMore = false;
                updateGraphView(1, false);
            }
        });
    }

    private boolean mIsStopLoadMore;

    private void updateGraphView(int page, boolean update) {
        if (page < 1 || mIsStopLoadMore) return;

        int position = mDivide * page;

        int nextPosition = position + page - mDivide - 1;

        if (position + page > getCountSize()) {
//            if (!update && mListener != null && getCountSize() > 1) {
            if (!update && mListener != null) {
                this.mCurrentPage = page;

                StepChartData next = getStepChartData(nextPosition + mDivide);
                if ( next != null ) {
                    mListener.getMoreData( next.getDate() );
                }

                return;
            } else {
//                mIsStopLoadMore = true;
            }
        }

//        this.mPosition = position + page - mDivide - 1;
        this.mPosition = nextPosition;

        this.mCurrentPage = page;
        this.mInterval = (float) ((mScreenWidth - mRangeRight - mRangeLeft) * 1.0 / mDivide);
        for (int i = mDivide, j = 0; i >= 0; i--, j++) {
            StepChartData data = getStepChartData(mPosition + i);
            long step = data.getSteps();
            if (step > mMaxValue) mMaxValue = step;
//            String date = data.getDay();
            mXPoints[j] = mRangeLeft + mInterval * j;
            mYPoints[j] = step;
            if (i != mDivide) {
                mDateTexts[j] = data.getDay();
            } else {
                mDateTexts[j] = getLeftText(data.getDate(), data.getDay());
            }
        }
        mMaxValue = ((mMaxValue + 99) / 100) * 100;
        this.mScale = (float) (mGraphHeight * 1.0 / mMaxValue);
        for (int i = 0; i <= mDivide; i++) {
            mYPoints[i] = mYAxisHeight - mYPoints[i] * mScale;
        }
        this.mPosition += mDivide;
        mGraphChartView.updateGraphChart(mDivide, mMaxValue, mXPoints, mYPoints, mDateTexts);
    }

    private String getLeftText(String date, String day) {
        String[] month = date.split("-");
        return month[1] + "." + day;
    }

    private StepChartData getStepChartData(int position) {
        StepChartData data = null;
        if (position >= getCountSize()) {
            try {
                data = mListStepData.get(getCountSize() - 1);
                String date = DateUtil.getCalendar(data.getDate(), data.getDay(), getCountSize() - position - 1);
                int last = date.lastIndexOf("-");
                data = new StepChartData(0, date.substring(last + 1), date.substring(0, last));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            data = mListStepData.get(position);
        }
        return data;
    }

    private int getCountSize() {
        return mListStepData.size();
    }

    public void addData(List<StepChartData> list, boolean update) {
        if (!update) {
            mCurrentPage = 1;

            mListStepData.clear();
            mStepKeySet.clear();

            mIsStopLoadMore = false;
        }
        if (list != null && !list.isEmpty() ) {
            Iterator<StepChartData> it = list.iterator();
            while ( it.hasNext() ) {
                StepChartData data = it.next();
                if ( data != null ) {
                    final String key = data.getDate() + "::" + data.getDay();
                    if ( !mStepKeySet.contains(key) ) {
                        mStepKeySet.add(key);
                        mListStepData.add(data);
                    }
                }
            }
        }
        updateGraphView(mCurrentPage, update);

    }

    public void setChartDataListener(ChartDataListener listener) {
        this.mListener = listener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof SwitchChartView) {
                child.layout(l + mLeftMargin, mTopMargin, mSwitchWidth + mLeftMargin, mTopMargin + mSwitchHeight);
            } else if (child instanceof GraphChartView) {
                child.layout(l, mRangeTop, r, b);
            } else {
                child.layout(l, 0, r, b);
            }
        }
    }

    private int mTouchX;
    private int mTouchY;
    private int mLastX;
    private int offsetX;
    private int mRawX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent( event );

        if ( mVelocityTracker == null ) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement( event );

        final float x = event.getX();
        final float y = event.getY();

        final VelocityTracker velocityTracker = mVelocityTracker;
        velocityTracker.computeCurrentVelocity( 1000 );
        int velocityX = (int)velocityTracker.getXVelocity();
        int velocityY = (int)velocityTracker.getYVelocity();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = (int) x;
                mTouchY = (int) y;
                mLastX = mTouchX;
                offsetX = 0;
                Log.i("onTouchEvent", "ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                mRawX = (int) x;
                offsetX = mRawX - mLastX;
                Log.i("onTouchEvent", "ACTION_MOVE");
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mTouchX >= mRangeLeft / 2 && mTouchY >= mRangeTop && mTouchY <= mRangeTop + mRangeBottom) {

//                    if (offsetX > DEFAULT_SLIDE_OFFSET) {
                    if (velocityX > DEFAULT_SNAP_VELOCITY) {
                        updateView(1);

//                    } else if (offsetX < -DEFAULT_SLIDE_OFFSET) {
                    } else if (velocityX < - DEFAULT_SNAP_VELOCITY) {
                        if (mCurrentPage > 1) {
                            mIsStopLoadMore = false;
                            updateView(-1);
                        }

                    } else {
                        // 绘制线上的小方框
                        int i = (int) ((mTouchX - mRangeLeft + mInterval / 2) / mInterval);
                        if (i > mDivide) {
                            i = mDivide;
                        } else if (i < 0) {
                            i = 0;
                        }

//                        String date;
//                        if (mPosition - i < getCountSize()) {
//                            date = String.valueOf(mListStepData.get(mPosition - i).getSteps());
//                        } else {
//                            date = "0";
//                        }

                        String date = "0";
                        StepChartData d = getChartData( mPosition - i );
                        if ( d != null ) {
                            date = String.valueOf(d.getSteps());
                        }

                        mLabelChartView.setLabelXY(mXPoints[i], mYPoints[i] + mRangeTop, date);
                    }
                } else {
                    mLabelChartView.setLabelXY(mTouchX, mTouchY, "");
                }

                if ( mVelocityTracker != null ) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }

                Log.i("onTouchEvent", "ACTION_UP");
                break;

        }
        return true;
    }

    private StepChartData getChartData(int position) {
        if ( mListStepData != null && !mListStepData.isEmpty()
                && position >= 0 && position < mListStepData.size() ) {
            try {
                return mListStepData.get( position );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setBorderYearMonth(String border) {
        mBorderYearMonth = border;
    }

    // 检测边界
    private boolean checkBorder(int page) {
        if (TextUtils.isEmpty(mBorderYearMonth)) return true;

        try {
            int position = mDivide * page;

            int nextPosition = position + page - mDivide - 1;

            int max = -1;
            for ( int i = nextPosition; i <= nextPosition + mDivide; i++ ) {
                StepChartData data = getStepChartData(i);
                int n = DateUtil.getYearMonthNumber(data.getDate());
                if ( n > max ) {
                    max = n;
                }
            }

            if ( max != -1 ) {
                int border = DateUtil.getYearMonthNumber(mBorderYearMonth);
                return border <= max;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    private void updateView(int offset) {
        final int dest = mCurrentPage + offset;

        if ( checkBorder( dest ) ) {
            updateGraphView(dest, false);
            mLabelChartView.setLabelXY(0, 0, null);
        } else {
            ToastUtils.showToast(getResources().getString(R.string.sports_kit_reach_final_page));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        height = height > getSuggestedMinimumHeight() ? height : getSuggestedMinimumHeight();
        int chartHeight = height - mTopMargin * 2 - mSwitchHeight;
        mGraphHeight = chartHeight - mChartPaddingTop - mChartPaddingBottom;
        mRangeBottom = mYAxisHeight = chartHeight - mChartPaddingBottom;

        mSwitchChartView.measure(mSwitchWidth, mSwitchHeight);
        mGraphChartView.measure(width, chartHeight);
        mLabelChartView.measure(width, height);

        setMeasuredDimension(width, height);

        if ( mPaint != null ) {
            LinearGradient linearGradient = new LinearGradient(0, 0, 0, getMeasuredHeight(),
                    0xff38abff, 0xff38e1ff, LinearGradient.TileMode.CLAMP);
            mPaint.setShader(linearGradient);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if ( mPaint != null ) {
            canvas.save();
            canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
            canvas.restore();
        }

//        if ( mDebugPaint != null ) {
//            canvas.save();
//            canvas.drawRect(mRangeLeft, mRangeTop, mRangeRight, mRangeBottom, mDebugPaint);
//            canvas.restore();
//        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        mXPoints = null;
        mYPoints = null;

        mDateTexts = null;
        mListStepData = null;
    }

    public interface ChartDataListener {
        void getMoreData(String yearMonth);
    }
}
