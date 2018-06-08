package com.liang.tind.www.tindtest.widget;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;

/**
 * created by Administrator
 * <p>
 * date 2018/6/6
 */
public class RotateImageView extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = "RotateImageView";

    public RotateImageView(Context context) {
        this(context, null);
    }

    public RotateImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }


    public void startRotate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            animate().rotationYBy(180).setDuration(1000).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    PropertyValuesHolder[] values = animation.getValues();
                    for (PropertyValuesHolder value : values) {
                        Log.e(TAG, "PropertyValuesHolder:  value==" + value);
                    }

                    Object animatedValue = animation.getAnimatedValue();
                    Log.e(TAG, "onAnimationUpdate: animatedValue=="+animatedValue);

                    long currentPlayTime = animation.getCurrentPlayTime();
                    Log.e(TAG, "currentPlayTime== "+currentPlayTime);

                    float animatedFraction = animation.getAnimatedFraction();
                    Log.e(TAG, "animatedFraction== "+animatedFraction);
                }
            }).start();
        }

//        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat("rotationY", 0, 180f);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, propertyValuesHolder)
//                .setDuration(1000);
//
//        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                PropertyValuesHolder[] values = animation.getValues();
//                for (PropertyValuesHolder value : values) {
//                    Log.e(TAG, "PropertyValuesHolder:  value==" + value);
//                }
//
//                Object animatedValue = animation.getAnimatedValue();
//                Log.e(TAG, "onAnimationUpdate: animatedValue==" + animatedValue);
//
//                long currentPlayTime = animation.getCurrentPlayTime();
//                Log.e(TAG, "currentPlayTime== " + currentPlayTime);
//
//                float animatedFraction = animation.getAnimatedFraction();
//                Log.e(TAG, "animatedFraction== " + animatedFraction);
//            }
//        });
//
//        objectAnimator.start();
    }
}
