package com.liang.tind.www.tindtest.util;

import android.content.Context;
import android.content.IntentFilter;

import com.liang.tind.www.tindtest.receiver.NetworkBroadcastReceiver;

/**
 * Created by xmll1 on 2018-03-06.
 */

public class BroadcaseReceiverFactory {
    private static NetworkBroadcastReceiver mReceiver;
    //注册网络变化广播
    public static void registerNetworkReceiver(NetworkBroadcastReceiver.NetEvent evevt,Context context){
        //注册广播
        mReceiver = new NetworkBroadcastReceiver(evevt);
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(mReceiver, filter);
    }
    //注销网络变化广播
    public static void unRegisterNetworkReceiver(Context context){
        context.unregisterReceiver(mReceiver);
        mReceiver = null;
    }


}
