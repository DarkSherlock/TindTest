package com.liang.tind.www.tindtest.activty;

import android.view.View;
import android.widget.TextView;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;
import com.tencent.bugly.crashreport.CrashReport;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by Administrator
 * <p>
 * date 2018/4/28
 */
public class TestBuglyAndResProguad extends BaseActivity {
    @BindView(R.id.tv_test)
    TextView mTvTest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_bugly;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String parse = dateFormat.format(date);
        CrashReport.putUserData(this, "Date", parse);

        mTvTest.setText("this is patch!");
        mTvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrashReport.testJavaCrash();
            }
        });
    }

}
