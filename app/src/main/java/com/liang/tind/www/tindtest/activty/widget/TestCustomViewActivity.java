package com.liang.tind.www.tindtest.activty.widget;

import android.view.View;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

/**
 * @author lonnie.liang
 * @date 2020/06/30 22:32
 */
public class TestCustomViewActivity extends BaseActivity {

  @Override
  protected int getLayoutId() {
    return R.layout.layout_custom_view;
  }

  @Override
  protected void init() {
    findViewById(R.id.custom)
        .setOnClickListener(View::invalidate);
  }
}
