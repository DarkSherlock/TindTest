package com.liang.tind.www.tindtest.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/** desc created by liangtiande date 2018/11/20 */
public class CustomView extends LinearLayout {
  private static final String TAG = "CustomView";
  private int i = 0;
  Paint mPaint = new Paint();

  public CustomView(Context context) {
    super(context);
    init();
  }

  public CustomView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mPaint.setColor(Color.RED);
    mPaint.setStrokeWidth(10);
    setClickable(true);
  }

  @Override
  public void invalidate() {
    super.invalidate();
    Log.i(TAG, "invalidate(): " + this);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //    canvas.drawLine(getPaddingStart(), 0, getWidth() - getPaddingEnd(), getHeight(), mPaint);
    //    Log.i(TAG, "onDraw(): " + this);
    canvas.drawCircle(10, 10, 10, mPaint);
  }

  private boolean isBollan = false;

  /**
   * test {@link}
   */
  public void test() {
    setPivotX(0);
    setPivotY(0);
    float scale;
    if (isBollan) {
      scale = 1.0f;
    } else {
      scale = 1.1f;
    }
    setScaleX(scale);
    setScaleY(scale);
//    setTranslationX(-10);
//    setTranslationY(-20);
    isBollan = !isBollan;
    Log.i(TAG, "test(): " + getPivotX() + "y" + getPivotY());
    //    setPadding(30,30,30,30);
    logRect();
  }

  public void scale() {
    float scale = 0;
    if (isBollan) {
      scale = 1.0f;
    } else {
      scale = 1.1f;
    }
    setScaleX(scale);
    setScaleY(scale);
    isBollan = !isBollan;
    logRect();
  }

  private void logRect() {
    Log.i(TAG, "matrix:" + getMatrix());
    RectF location = new RectF(getLeft(), getTop(), getRight(), getBottom());
    Log.i(TAG, "location(): " + location);
    getMatrix().mapRect(location);
    Log.i(TAG, "mapRect(): " + location);
    Rect hit = new Rect();
    getHitRect(hit);
    Log.i(TAG, "hit rect: "+hit);

    postDelayed(
        () ->
            Log.i(
                TAG,
                "left:"
                    + getLeft()
                    + ",top:"
                    + getTop()
                    + ",bottom:"
                    + getBottom()
                    + ",right:"
                    + getRight()),
        200);
  }
}
