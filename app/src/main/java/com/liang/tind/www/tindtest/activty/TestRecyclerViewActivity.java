package com.liang.tind.www.tindtest.activty;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * desc
 * created by liangtiande
 * date 2018/11/5
 */
public class TestRecyclerViewActivity extends BaseActivity {
    private RecyclerView mRvBirthdayUsers;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_recycler_view;
    }

    @Override
    protected void init() {
//        TestRvAdapter adapter = new TestRvAdapter();
        mRvBirthdayUsers = findViewById(R.id.rv_birthday_users);
        mRvBirthdayUsers.setLayoutManager(new LinearLayoutManager(this));

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            list.add("test"+i);
        }
//        mRvBirthdayUsers.setAdapter(adapter);
//        adapter.setList(list);
//        Observable.timer(1,TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                Log.e(TAG, "accept(TestRecyclerViewActivity.java:45): setList");
//                adapter.setList(list);
//            }
//        });

    }
}
