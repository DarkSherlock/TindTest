package com.liang.tind.www.tindtest.activty

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.liang.tind.www.tindtest.R
import com.liang.tind.www.tindtest.base.BaseActivity
import java.util.*

/**
 * desc
 * created by liangtiande
 * date 2018/11/5
 */
class TestRecyclerViewActivity : BaseActivity() {
    private var mRvBirthdayUsers: RecyclerView? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_test_recycler_view
    }

    override fun init() {
        //        TestRvAdapter adapter = new TestRvAdapter();
        mRvBirthdayUsers = findViewById(R.id.rv)
        mRvBirthdayUsers!!.layoutManager = LinearLayoutManager(this)

        val list = ArrayList<String>()
        for (i in 0..4) {
            list.add("test$i")
        }

        mRvBirthdayUsers!!.adapter = RvAdapter(list, this)
    }


    internal class RvAdapter(private val mList: List<String>, private val mContext: Context) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val frameLayout = FrameLayout(mContext)
            frameLayout.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val inflate = LayoutInflater.from(mContext).inflate(R.layout.item_nested_rv, frameLayout)
            frameLayout.setOnClickListener { view -> Log.i("Tind", "on Parent RV click") }
            return ViewHolder(frameLayout)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = mList[position]
            val list = ArrayList<String>()
            for (i in 0..2) {
                list.add(item + i)
            }
            holder.setData(list)
        }

        override fun getItemCount(): Int {
            return mList.size
        }


        internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var mRecyclerView: RecyclerView
            lateinit var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

            init {
                val root = itemView as FrameLayout
                mRecyclerView = root.getChildAt(0) as RecyclerView
                //                mRecyclerView.setClickable(false);
                //                mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
                //                    @Override
                //                    public boolean onTouch(View v, MotionEvent event) {
                //                        boolean b = root.onTouchEvent(event);
                //                        return b;
                //                    }
                //                });
                mRecyclerView.parent.requestDisallowInterceptTouchEvent(false)
            }

            fun setData(list: List<String>) {
                mAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                        val textView = TextView(itemView.context)
                        return object : RecyclerView.ViewHolder(textView) {

                        }
                    }

                    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                        val itemView = holder.itemView as TextView
                        itemView.text = list[position]
                    }

                    override fun getItemCount(): Int {
                        return list.size
                    }
                }

                mRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
                mRecyclerView.adapter = mAdapter
            }
        }
    }
}
