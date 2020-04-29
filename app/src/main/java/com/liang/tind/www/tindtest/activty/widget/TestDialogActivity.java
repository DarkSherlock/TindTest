package com.liang.tind.www.tindtest.activty.widget;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;
import com.liang.tind.www.tindtest.util.SoftHideUtil;

/**
 * @author lonnie.liang
 * @date 2020/03/13 11:12
 */
public class TestDialogActivity extends BaseActivity {
  @Override
  protected int getLayoutId() {
    return R.layout.activity_dialog_activity;
  }

  @Override
  protected void init() {
    //        Window window = getWindow();
    //        WindowManager.LayoutParams attributes = window.getAttributes();
    //        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
    //        attributes.horizontalMargin = UIUtil.dip2px(this,15);
    //        window.setAttributes(attributes);
//    getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
//                                WindowManager.LayoutParams.WRAP_CONTENT);
    SoftHideUtil.INSTANCE.enableAdjustResizeInFullscreen(this);
  }
}
