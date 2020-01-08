package com.liang.tind.www.tindtest.activty;

import android.os.Build;
import android.util.Log;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

public class EmptyActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_empty;
    }

    @Override
    protected void init() {
        Log.i(TAG, "init(EmptyActivity.java:14): ========>");
        LinkedHashMap<Integer, Integer> linkedHashMap =
                new LinkedHashMap<>(8, 0.75f, false);
        linkedHashMap.put(1,1);
        linkedHashMap.put(2,2);
        linkedHashMap.put(3,3);
        linkedHashMap.put(4,4);
        linkedHashMap.put(5,5);

        linkedHashMap.get(4);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            linkedHashMap.forEach(new BiConsumer<Integer, Integer>() {
                @Override
                public void accept(Integer integer, Integer integer2) {
                    Log.i(TAG, "accept(EmptyActivity.java:32): "+integer);
                }
            });
        }
    }

}
