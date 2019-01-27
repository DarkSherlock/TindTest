package com.liang.tind.www.tindtest.activty.widget;

import android.widget.TextView;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

/**
 * desc
 * created by liangtiande
 * date 2019/1/22
 */
public class TestOnTouchEventActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_ontouch_event;
    }

    @Override
    protected void init() {
        TextView text = findViewById(R.id.text);

    }

}
