package com.liang.tind.www.tindtest.activty;

import android.util.Log;

import com.liang.tind.www.tindtest.base.BaseActivity;

import io.reactivex.subjects.PublishSubject;

public class EmptyActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void init() {
        Log.i(TAG, "init(EmptyActivity.java:14): ========>");

        PublishSubject<Integer> subject = PublishSubject.create();
        subject.subscribe(System.out::println);

    }
}
