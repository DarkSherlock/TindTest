package com.liang.tind.www.tindtest.activty.widget;

import android.util.Log;
import android.widget.FrameLayout;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

/**
 * @author lonnie.liang
 * @date 2020/10/12 17:21
 */
public class TestNinePatch extends BaseActivity {

  @Override
  protected int getLayoutId() {
    return R.layout.layout_controller_waiting_room_popup_view;
  }

  @Override
  protected void init() {
    FrameLayout root = findViewById(R.id.root);
    root.postDelayed(
        () -> {
          Log.i(
              TAG,
              "left:"
                  + root.getPaddingLeft()
                  + "top:"
                  + root.getPaddingTop()
                  + "right:"
                  + root.getPaddingRight()
                  + "bottom:"
                  + root.getPaddingBottom());
//          root.setTranslationY(root.getPaddingBottom());
        },
        200);
  }
}
