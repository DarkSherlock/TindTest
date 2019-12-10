package com.liang.tind.www.tindtest.activty.widget;

import android.view.View;
import android.widget.Button;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc
 * created by liangtiande
 * date 2019/9/12
 */
public class TestMotionLayoutActivity extends BaseActivity {
    @BindView(R.id.button)
    Button mButton;
//    @BindView(R.id.motion_layout)
//    MotionLayout mMotionLayout;
    /**
     * button 是否在top位置
     */
    private boolean isTop;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_motion_layout;
    }

    @Override
    protected void init() {
//        mMotionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
//            @Override
//            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {
//                Log.i(TAG, "onTransitionStarted: i="+i+",i1="+i1);
//            }
//
//            @Override
//            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {
//                Log.i(TAG, "onTransitionChange: i="+i+",i1="+i1+",v="+v);
//            }
//
//            @Override
//            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
//                Log.i(TAG, "onTransitionCompleted : i="+i);
//            }
//
//            @Override
//            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {
//                Log.i(TAG, "onTransitionTrigger:i="+i+",b="+b+",v="+v);
//            }
//        });

    }

    @OnClick(R.id.button)
    public void startMotionAnimation(View v) {
//        if (isTop) {
//            mMotionLayout.transitionToStart();
//        } else {
//            mMotionLayout.transitionToEnd();
//        }
        isTop = !isTop;
    }
}
