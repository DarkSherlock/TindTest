package com.liang.tind.www.tindtest.activty;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

/**
 * created by Administrator
 * <p>
 * date 2018/4/24
 */
public class TestRbtnActivity extends BaseActivity {

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            boolean obj = (boolean) msg.obj;
            Log.e(TAG, "handleMessage: "+obj);
            return false;
        }
    });
    private EditText mEditText;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_rbtn;
    }

    @Override
    protected void init() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Message obtain = Message.obtain(mHandler, 0, false);
//                obtain.sendToTarget();
//                new AlertDialog.Builder(TestRbtnActivity.this)
//                        .setTitle("测试声明周期方法")
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .show();
                Dialog dialog = new Dialog(TestRbtnActivity.this);
                dialog.setTitle("测试");
                dialog.show();
            }
        });
        LinearLayout root= findViewById(R.id.root);
        root.setFocusable(true);
        root.setFocusableInTouchMode(true);
        root.requestFocus();

        mEditText = findViewById(R.id.et_test);
        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i(TAG, "onFocusChange: "+hasFocus);
            }
        });

        String url = "https://www.csdn.net/";
        StringBuilder stringBuilder = new StringBuilder(url);

        int index = stringBuilder.lastIndexOf("/");
        if (index!=-1){
            stringBuilder.deleteCharAt(index);
            Log.e(TAG, "init: "+stringBuilder.toString());
        }
        stringBuilder.append("?");
        stringBuilder.append("com_nd_appid");
        stringBuilder.append("=");
        stringBuilder.append("test");

        Log.e(TAG, "init: url=="+stringBuilder.toString());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //设置点击edittext外部隐藏输入法键盘
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                mEditText.clearFocus();
                InputMethodManager manager = (InputMethodManager) mEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
