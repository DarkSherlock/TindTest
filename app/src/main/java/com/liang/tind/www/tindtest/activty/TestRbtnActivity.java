package com.liang.tind.www.tindtest.activty;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Message obtain = Message.obtain(mHandler, 0, false);
                obtain.sendToTarget();
            }
        });
    }
}
