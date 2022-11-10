package com.liang.tind.www.tindtest.activty

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.liang.tind.www.tindtest.R
import com.liang.tind.www.tindtest.base.BaseActivity
import com.liang.tind.www.tindtest.widget.CustomView
import com.liang.tind.www.tindtest.widget.LinearItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

/**
 * desc
 * created by liangtiande
 * date 2018/11/5
 */
class TestRecyclerViewActivity : BaseActivity(), CoroutineScope by MainScope() {
    private var mRvBirthdayUsers: RecyclerView? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_test_recycler_view
    }

    override fun init() {
        //        TestRvAdapter adapter = new TestRvAdapter();
        mRvBirthdayUsers = findViewById(R.id.rv)

        mRvBirthdayUsers!!.layoutManager = LinearLayoutManager(this)
        this.intent = null
        val list = ArrayList<String>()
        for (i in 0..4) {
            list.add("test$i")
        }

        mRvBirthdayUsers!!.adapter = RvAdapter(list, this)
//        mRvBirthdayUsers?.itemAnimator = RecyclerView.ItemAnimator()
        mRvBirthdayUsers?.addItemDecoration(LinearItemDecoration(10, 20))
    }


    internal class RvAdapter(private val list: List<String>, private val context: Context) :
        RecyclerView.Adapter<RvAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false)
            val holder = ViewHolder(view, this)
            Log.i("Tind", "onCreateViewHolder:$holder ")
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Log.i("Tind", "onBindViewHolder: $position，holder：$holder")
        }

        override fun getItemCount(): Int {
            return list.size
        }

        internal class ViewHolder(itemView: View, adapter: RvAdapter) :
            RecyclerView.ViewHolder(itemView) {
            private val testA: String = ""
            private var flagA = true

            init {
                (itemView as? CustomView)?.setOnClickListener {
//                    itemView.test()
                    adapter.notifyItemChanged(layoutPosition)
                }
            }

            constructor(params1: String, itemView: View, adapter: RvAdapter) : this(
                itemView,
                adapter
            ) {
                Log.i("TAG", "params1: $params1")
            }

            private val testB: String = ""
            private var flagB = true
        }
    }

}
