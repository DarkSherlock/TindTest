package com.liang.tind.www.tindtest.activty.widget;

import android.util.Log;

import com.liang.tind.www.tindtest.activty.MainActivity;
import com.liang.tind.www.tindtest.activty.TestBarChartActivity;
import com.liang.tind.www.tindtest.activty.TestLineChartActivity;
import com.liang.tind.www.tindtest.activty.TestRbtnActivity;
import com.liang.tind.www.tindtest.activty.TestRecyclerViewActivity;
import com.liang.tind.www.tindtest.activty.TestRotateImageViewActivity;

/**
 * desc
 * created by liangtiande
 * date 2019/1/22
 */
public class TestViewActivity extends MainActivity {
    private static final String TAG = "TestViewActivity";
    @Override
    protected void initData() {

        addItem(TestOnTouchEventActivity.class);
        addItem(TestRecyclerViewActivity.class);
        addItem(TestLineChartActivity.class);
        addItem(TestBarChartActivity.class);
        addItem(TestWidgetActivity.class);
        addItem(TestRbtnActivity.class);
        addItem(TestRotateImageViewActivity.class);
        addItem(TestRvActivity.class);
        addItem(TestSoftInputActivity.class);
        addItem(TestMotionLayoutActivity.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG, "onBackPressed(TestViewActivity.java:32): ");
    }
}
