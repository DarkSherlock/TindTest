package com.liang.tind.www.tindtest.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

import java.util.Objects;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

/**
 * desc
 * created by liangtiande
 * date 2019/8/7
 */
public class SelectorUtils {

    /**
     * 主要针对的是ImageView设置图片，
     * 图片资源为一张，通过tint来修改不同状态时显示的不同背景，
     * 以达到节约资源，减少内存的目的
     *
     * @param activity      当前的Activity或者Fragment
     * @param view          需要修改的View，主要只ImageView
     * @param drawableRes   drawable资源id
     * @param normalColor   正常时的颜色
     * @param selectorColor 选中时的颜色
     */
    public static void viewSetSelector(Activity activity, View view, int drawableRes, int normalColor, int selectorColor) {
        Drawable drawable = ContextCompat.getDrawable(activity, drawableRes);
        //获得选中颜色和非选中颜色
        int colors[] = new int[]{ContextCompat.getColor(activity, selectorColor),
                ContextCompat.getColor(activity, normalColor)};
        //点击状态数组
        int states[][] = new int[2][];

        //点击状态
        states[0] = new int[]{android.R.attr.state_pressed};
        //非点击状态
        states[1] = new int[]{};
        //存放状态值和颜色
        ColorStateList colorStateList = new ColorStateList(states, colors);

        //存放相应状态和drawable
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(states[0], drawable);
        stateListDrawable.addState(states[1], drawable);

        Drawable.ConstantState state = stateListDrawable.getConstantState();
        drawable = DrawableCompat.wrap(Objects.requireNonNull(state == null ? drawable : state.newDrawable())).mutate();

        DrawableCompat.setTintList(drawable, colorStateList);
        view.setClickable(true);
        //改变背景Drawable
        view.setBackground(drawable);
        //如果是ImageView，可以设置src相关
        //view.setImageDrawable(drawable);
    }

    /**
     * 改变View的状态的图示,通过Tint减少资源和内存
     *
     * @param context    当前Activity或者Fragment
     * @param view        当前需要改变的View
     * @param drawableRes 资源id
     * @param colorRes    color资源
     */
    public static void changeViewState(Context context, View view, int drawableRes, int colorRes) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableRes);

        int color = ContextCompat.getColor(context, colorRes);

        Drawable.ConstantState state = Objects.requireNonNull(drawable).getConstantState();

        drawable = DrawableCompat.wrap(state == null ? drawable : state.newDrawable()).mutate();

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        DrawableCompat.setTint(drawable, color);

        view.setBackground(drawable);
        //如果是ImageView，可以设置src相关
        //view.setImageDrawable(drawable);
    }
}
