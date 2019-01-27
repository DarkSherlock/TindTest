package com.liang.tind.www.tindtest.activty;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

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
        for (int i = 0; i < 5; i++) {
            list.add("test" + i);
        }

        mRvBirthdayUsers.setAdapter(new RvAdapter(list, this));
    }


    public static class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
        private List<String> mList;
        private Context mContext;

        public RvAdapter(List<String> list, Context context) {
            mList = list;
            mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            FrameLayout frameLayout = new FrameLayout(mContext);
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_nested_rv, frameLayout);
            frameLayout.setOnClickListener(view -> Log.i("Tind", "on Parent RV click"));
            return new ViewHolder(frameLayout);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String item = mList.get(position);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add(item + i);
            }
            holder.setData(list);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            RecyclerView mRecyclerView;
            RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;

            public ViewHolder(View itemView) {
                super(itemView);
                FrameLayout root = (FrameLayout) itemView;
                mRecyclerView = (RecyclerView) root.getChildAt(0);
//                mRecyclerView.setClickable(false);
//                mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        boolean b = root.onTouchEvent(event);
//                        return b;
//                    }
//                });
                mRecyclerView.getParent().requestDisallowInterceptTouchEvent(false);
            }

            public void setData(List<String> list) {
                mAdapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                    @NonNull
                    @Override
                    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        TextView textView = new TextView(itemView.getContext());
                        return new RecyclerView.ViewHolder(textView) {

                        };
                    }

                    @Override
                    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                        TextView itemView = (TextView) holder.itemView;
                        itemView.setText(list.get(position));
                    }

                    @Override
                    public int getItemCount() {
                        return list.size();
                    }
                };

                mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }
}
