package com.liang.tind.www.tindtest.activty;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;
import com.liang.tind.www.tindtest.widget.DigitsTextWatcher;

/**
 * created by Administrator
 *
 * <p>date 2018/4/24
 */
public class TestEditTextActivity extends BaseActivity {

    private EditText mEditText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_edittext;
    }

    @Override
    protected void init() {

        mEditText = findViewById(R.id.et_test);
        //        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        //            @Override
        //            public void onFocusChange(View v, boolean hasFocus) {
        //                Log.i(TAG, "onFocusChange: "+hasFocus);
        //            }
        //        });

        mEditText.addTextChangedListener(new DigitsTextWatcher(mEditText, 2));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //设置点击edittext外部隐藏输入法键盘
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                mEditText.clearFocus();
                InputMethodManager manager = (InputMethodManager)
                        mEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
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
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            // 点击的是输入框区域，保留点击EditText的事件
            return !(event.getX() > left)
                    || !(event.getX() < right)
                    || !(event.getY() > top)
                    || !(event.getY() < bottom);
        }
        return false;
    }
}
