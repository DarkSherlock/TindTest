package com.liang.tind.www.tindtest.activty.widget;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

/**
 * @author 梁天德
 * @date 2019/11/06 14:25
 * desc
 */
public class TestSwitchActivity extends BaseActivity {
    private androidx.appcompat.widget.SwitchCompat mSwitchTest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_switch;
    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        mSwitchTest = findViewById(R.id.switch_test);
    }
}
