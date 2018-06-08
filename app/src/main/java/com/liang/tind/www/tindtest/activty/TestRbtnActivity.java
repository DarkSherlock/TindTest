package com.liang.tind.www.tindtest.activty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

/**
 * created by Administrator
 * <p>
 * date 2018/4/24
 */
public class TestRbtnActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_rbtn;
    }

    @Override
    protected void init() {
        RadioButton radioButton = findViewById(R.id.button);
        radioButton.getParent().requestDisallowInterceptTouchEvent(false);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        radioButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "onTouch: ");
                return false;
            }
        });
//        int FLAG_DISALLOW_INTERCEPT = 0x80000;
//        int mGroupFlags = 0;
//        final boolean disallowIntercept;
//        mGroupFlags |= FLAG_DISALLOW_INTERCEPT;
//        disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
//        Log.e(TAG, "init: mGroupFlags==" + mGroupFlags);
//        Log.e(TAG, "init: disallowIntercept==" + disallowIntercept);
//
//        mGroupFlags = 0;
//        mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
//        boolean disallow = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
//        Log.e(TAG, "init: mGroupFlags==" + mGroupFlags);
//        Log.e(TAG, "init: disallow==" + disallow);


    }
}
