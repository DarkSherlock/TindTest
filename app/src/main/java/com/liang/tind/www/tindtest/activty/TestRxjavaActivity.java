package com.liang.tind.www.tindtest.activty;

import android.util.Log;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import org.reactivestreams.Subscription;

import java.util.HashSet;

/**
 * created by Administrator
 * <p>
 * date 2018/6/4
 */
public class TestRxjavaActivity extends BaseActivity {
    public android.os.Handler mHandler = new android.os.Handler();

    private boolean isComplete = true;
    Subscription mDisposable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_rxjava;
    }

    @Override
    protected void init() {
//        List<String> strings = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            strings.add(String.valueOf(i));
//        }
//        strings.add(String.valueOf(1));
//        strings.remove("1");
//        strings.remove("1");
//        Log.e(TAG, "strings="+strings);
//
        HashSet<String > set = new HashSet<>();
        set.add("1");
        set.add("1");


        try {
            getTest(1);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private boolean getTest(int i) throws Throwable {
        if (i==7){
           throw new Throwable("test");
        }
        if (1==5){
            return true;
        }
        Log.e(TAG, "getTest: ");
        Log.e(TAG, "getTest: ");
        Log.e(TAG, "getTest: ");
        Log.e(TAG, "getTest: ");
        return false;
    }

}
