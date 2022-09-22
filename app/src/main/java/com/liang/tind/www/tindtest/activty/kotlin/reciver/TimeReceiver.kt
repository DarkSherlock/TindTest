package com.liang.tind.www.tindtest.activty.kotlin.reciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 *@author lonnie.liang
 *@date 2020/04/08 17:56
 *
 */
class TimeReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){
            Intent.ACTION_TIME_CHANGED -> Log.i("Tind","intent=$intent")
            Intent.ACTION_TIME_TICK -> Log.i("Tind","tick intent=$intent,${SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())}")
        }
    }
}