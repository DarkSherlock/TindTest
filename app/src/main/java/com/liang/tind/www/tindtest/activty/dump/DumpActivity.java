package com.liang.tind.www.tindtest.activty.dump;

import android.util.Log;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;
/**
 * @author lonnie.liang
 * @date 2020/04/30 10:55
 */
public class DumpActivity extends BaseActivity {
  public static final String TINd = "tind";
  private static int[] arr = new int[100000];
  private static int[] ano = new int[50000];


  @Override
  protected int getLayoutId() {
    return R.layout.activity_dump;
  }

  @Override
  protected void init() {
    Log.i(TAG, "init(): " + TINd);
    findViewById(R.id.btn_release)
        .setOnClickListener(
            v -> {
              if (arr != null) {
                arr = null;
                System.gc();
              } else {
                arr = new int[1000];
              }
            });
  }
}
