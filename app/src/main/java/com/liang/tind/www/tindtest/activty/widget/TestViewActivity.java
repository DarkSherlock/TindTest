package com.liang.tind.www.tindtest.activty.widget;

import android.util.Log;

import com.liang.tind.www.tindtest.activty.MainActivity;
import com.liang.tind.www.tindtest.activty.TestBarChartActivity;
import com.liang.tind.www.tindtest.activty.TestLineChartActivity;
import com.liang.tind.www.tindtest.activty.TestEditTextActivity;
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
        addItem(TestEditTextActivity.class);
        addItem(TestRotateImageViewActivity.class);
        addItem(TestRvActivity.class);
        addItem(TestSoftInputActivity.class);
        addItem(TestMotionLayoutActivity.class);
        addItem(TestSwitchActivity.class);
        addItem(TestDialogActivity.class);
        addItem(TestRatingBarActivity.class);
        addItem(TestCustomViewActivity.class);
        addItem(TestNinePatch.class);
        addItem(LevelListDrawableActivity.class);
        addItem(TestHtmlActivity.class);
        addItem(SnackBarActivity.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG, "onBackPressed(TestViewActivity.java:32): ");
    }
}
