package com.liang.tind.www.tindtest.activty.widget;

import android.content.res.ColorStateList;
import android.widget.CompoundButton;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.drawable.DrawableCompat;

/**
 * @author 梁天德
 * @date 2019/11/06 14:25
 * desc
 */
public class TestSwitchActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
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

        SwitchCompat switchCompat = findViewById(R.id.switch_test);
        switchCompat.setOnCheckedChangeListener(this);
        setSwitchColor(switchCompat);
    }

    public static void setSwitchColor(SwitchCompat v) {

        // thumb color
        int thumbColor = 0xffff0000;

        // trackColor
        int trackColor = 0xffdddddd;

        // set the thumb color
        DrawableCompat.setTintList(v.getThumbDrawable(), new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{}
                },
                new int[]{
                        thumbColor,
                        thumbColor
                }));

        // set the track color
        DrawableCompat.setTintList(v.getTrackDrawable(), new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{}
                },
                new int[]{
                        thumbColor,
                        trackColor
                }));


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
