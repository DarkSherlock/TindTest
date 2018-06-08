package com.liang.tind.www.tindtest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.liang.tind.www.tindtest.util.NetworkUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义检查手机网络状态是否切换的广播接受器
 *
 * @author cl
 */
public class NetworkBroadcastReceiver extends BroadcastReceiver {
    private List<NetEvent> mNetEvents = new ArrayList<>();
    private static final String TAG = "NetworkBroadcastReceive";

    public NetworkBroadcastReceiver(NetEvent event) {
       addNetEventListener(event);
    }

    public void addNetEventListener(NetEvent listener) {
        mNetEvents.add(listener);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果相等的话就说明网络状态发生了变化
        try {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                int netWorkState = NetworkUtils.getNetWorkState(context);
                for (NetEvent listener : mNetEvents) {
                    // 接口回调传过去状态的类型
                    listener.onNetChange(netWorkState);
                }

//                RxBus.getInstance().post(netWorkState);
                Log.e(TAG, "onReceive: 网络状态发生了变化");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 自定义接口
    public interface NetEvent {
        void onNetChange(int netType);
    }
}
