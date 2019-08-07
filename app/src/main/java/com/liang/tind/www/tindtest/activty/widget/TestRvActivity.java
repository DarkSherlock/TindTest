package com.liang.tind.www.tindtest.activty.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc
 * created by liangtiande
 * date 2019/7/26
 */
public class TestRvActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView mRv;

    List<String> mList = new ArrayList<>();

    public static final int DEFAULT_COUNT = 10;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_recycler_view;
    }
    int add;
    int remove;
    @Override
    protected void init() {
        ButterKnife.bind(this);

        for (int i = 0; i < DEFAULT_COUNT; i++) {
            mList.add("Test Data "+i);
        }

        Adapter adapter = new Adapter(mList,this);
        adapter.setOnAdd((v,position) -> {
            mList.add(position,"Test Data Add"+ add++);
            Log.i(TAG, "onAdd: position="+position);
            adapter.notifyItemInserted(position);
        });

        adapter.setOnRemove((v,position) -> {
            String remove = mList.remove(position);
            Log.i(TAG, "Remove: position"+position+"data="+remove);
            adapter.notifyItemRemoved(position);
//            adapter.notifyItemChanged(position);
        });

        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.addItemDecoration(new LinearItemDecoration(15,15));
        mRv.setAdapter(adapter);
    }


    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {
        private static final String TAG = "Adapter";
        private List<String> mList;
        private Context mContext;
        private OnItemAddListener onAdd;
        private OnItemRemoveListener onRemove;

        public Adapter(@NonNull List<String> list, @NonNull Context context) {
            mList = list;
            mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.i(TAG, "onCreateViewHolder =========>");
            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setBackgroundColor(Color.parseColor("#ff99cc00"));
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
//            params.setMargins(30,30,30,30);
            linearLayout.setLayoutParams(params);



            ViewHolder viewHolder = new ViewHolder(linearLayout);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Log.i(TAG, "onBindViewHolder(Adapter.java:80): position="+position+",data="+mList.get(position));
            holder.mAdd.setOnClickListener(v -> {
                onAdd.onAdd(v,holder.getAdapterPosition());
            });
            holder.mRemove.setOnClickListener(v -> {
                onRemove.onRemove(v,holder.getAdapterPosition());
            });

            holder.mTextView.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public void setOnAdd(OnItemAddListener onAdd) {
            this.onAdd = onAdd;
        }

        public void setOnRemove(OnItemRemoveListener onRemove) {
            this.onRemove = onRemove;
        }

        public static interface OnItemAddListener{
            void onAdd(View view, int position);
        }

        public static interface OnItemRemoveListener{
            void onRemove(View view, int position);
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextView;
        public final Button mAdd;
        public final Button mRemove;

        public ViewHolder(@NonNull ViewGroup itemView) {
            super(itemView);
            Context context = itemView.getContext();
            mTextView = new TextView(context);
            mTextView.setBackgroundColor(Color.parseColor("#ffff8800"));
            FrameLayout.LayoutParams wrapParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
            wrapParams.setMargins(30, 30, 30, 30);
            mTextView.setLayoutParams(new FrameLayout.LayoutParams(wrapParams));

            mAdd = new Button(context);
            mAdd.setText("add");
            mAdd.setLayoutParams(wrapParams);

            mRemove = new Button(context);
            mRemove.setText("remove");
            mRemove.setLayoutParams(wrapParams);


            itemView.addView(mTextView);
            itemView.addView(mAdd);
            itemView.addView(mRemove);
        }
    }
}