package com.liang.tind.www.tindtest.activty.kotlin

import android.util.Log
import com.liang.tind.www.tindtest.R
import com.liang.tind.www.tindtest.base.BaseActivity
import com.liang.tind.www.tindtest.base.TindApplication
import com.liang.tind.www.tindtest.util.IListenerHolder
import com.liang.tind.www.tindtest.util.ListenerHolder


/**
 * desc
 * created by liangtiande
 * date 2019/1/22
 */
class KotlinActivity : BaseActivity(),
    IListenerHolder<KotlinActivity.Listener> by ListenerHolder() {

    override fun getLayoutId() = R.layout.activity_kotlin

    override fun init() {
        TindApplication.list.add(this)
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ",
        // Locale.getDefault());
        //
        //        String time = sdf.format(new Date());
        //        Log.e(TAG, "quickTest(MainActivity.java:128): "+time);
        //        System.out.println(time);
        //
        //        //解析时间 2016-01-05T15:09:54Z
        //        Date date = null;
        //        try {
        //            date = sdf.parse(time);
        //        } catch (ParseException e) {
        //            e.printStackTrace();
        //        }
        //        Log.e(TAG, "quickTest(MainActivity.java:138): "+date);
        //        System.out.println(date);
        //        List<Integer> list = new ArrayList<>();
        //        list.add(0);
        //
        //        list.add(2);
        //
        //        list.add(3);
        //        list.add(8);
        //        list.add(1);
        //        list.add(6);
        ////        list.add(1,1);
        //
        //        Log.i(TAG, "quickTest(MainActivity.java:130): "+list);
        //        Collections.sort(list, new Comparator<Integer>() {
        //            @Override
        //            public int compare(Integer o1, Integer o2) {
        //                return o1 - o2;
        //            }
        //        });
        //
        //        Log.i(TAG, "quickTest(MainActivity.java:141): "+list);
        testCallback()
        testListenerController()
    }

    private fun testListenerController() {
        val listener = object : Listener {
            override fun onStart() {
                Log.i(TAG, "onStart: ")
            }

            override fun onEnd(code: Int) {
                Log.i(TAG, "onEnd: code=$code")
            }
        }

        this.registerListener(listener)
        val code = 1
        this.notifyEach {
            onStart()
            onEnd(code)
        }

        this.unregisterListener(listener)
        this.notifyEach {
            onStart()
            onEnd(code)
        }
    }

    private val callbackController = ListenerHolder<Callback>()
    private fun testCallback() {
        callbackController.registerListener {
            Log.i(TAG, "callback:code$it ")
        }

        callbackController.notifyEach {
            invoke("-1")
        }
    }

    private interface Listener {
        fun onStart()
        fun onEnd(code: Int)
    }

}

private typealias Callback = (code: String) -> Unit