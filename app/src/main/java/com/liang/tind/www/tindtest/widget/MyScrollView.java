package com.liang.tind.www.tindtest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author lonnie.liang
 * @date 2020/09/09 15:11
 */
class MyScrollView extends ScrollView {
  private static final String TAG = "MyMyScrollView";

  public MyScrollView(Context context) {
    super(context);
  }

  public MyScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    boolean result = super.dispatchTouchEvent(ev);
    Log.i(TAG, "dispatchTouchEvent(): " + result);
    return result;
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
      boolean result = super.onTouchEvent(ev);
      Log.i(TAG, "onTouchEvent(): " + result);
      return result;
  }

  @Override
  public boolean onHoverEvent(MotionEvent event) {
      boolean result = super.onHoverEvent(event);
      Log.i(TAG, "onHoverEvent(): " + result);
      return result;
  }

  @Override
  public boolean onInterceptHoverEvent(MotionEvent ev) {
      boolean result = super.onInterceptHoverEvent(ev);
      Log.i(TAG, "onInterceptHoverEvent(): " + result);
      return result;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
      boolean result = super.onInterceptTouchEvent(ev);
      Log.i(TAG, "onInterceptTouchEvent(): " + result);
      return result;
  }
}
