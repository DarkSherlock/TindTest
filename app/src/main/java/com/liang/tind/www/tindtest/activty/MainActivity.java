package com.liang.tind.www.tindtest.activty;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.liang.tind.www.tindtest.receiver.NetworkBroadcastReceiver;
import com.liang.tind.www.tindtest.util.NetworkUtils;
import com.liang.tind.www.tindtest.util.SocketClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ListActivity implements NetworkBroadcastReceiver.NetEvent {
    public static final String KEY_CLASS = "class";
    public static final String KEY_SIMPLE_NAME = "simple_name";
    private List<HashMap<String, String>> mDatas;
    private static final String TAG = "MainActivity";
    public static final String SOCKET_SERVER_URL = "ws://47.96.31.242:7100";
    private HashMap<String, String> mHashMap;

    //    public static final String SOCKET_SERVER_URL = "ws://192.168.8.41:7100";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        BroadcaseReceiverFactory.registerNetworkReceiver(this, this);
//        SocketClient.getInstance().connectToServer(SOCKET_SERVER_URL);
        //1.数据源
        mDatas = new ArrayList<>();

        mHashMap = new HashMap<>();
        mHashMap.put(KEY_CLASS, TestSocketActivity.class.getName());
        mHashMap.put(KEY_SIMPLE_NAME, TestSocketActivity.class.getSimpleName());
        mDatas.add(mHashMap);

        mHashMap = new HashMap<>();
        mHashMap.put(KEY_CLASS, TestChromiumActivity.class.getName());
        mHashMap.put(KEY_SIMPLE_NAME, TestChromiumActivity.class.getSimpleName());
        mDatas.add(mHashMap);

        mHashMap = new HashMap<>();
        mHashMap.put(KEY_CLASS, TestHRFace.class.getName());
        mHashMap.put(KEY_SIMPLE_NAME, TestHRFace.class.getSimpleName());
        mDatas.add(mHashMap);

        mHashMap = new HashMap<>();
        mHashMap.put(KEY_CLASS, TestBuglyAndResProguad.class.getName());
        mHashMap.put(KEY_SIMPLE_NAME, TestBuglyAndResProguad.class.getSimpleName());
        mDatas.add(mHashMap);

        mHashMap = new HashMap<>();
        mHashMap.put(KEY_CLASS, TestRxjavaActivity.class.getName());
        mHashMap.put(KEY_SIMPLE_NAME, TestRxjavaActivity.class.getSimpleName());
        mDatas.add(mHashMap);

        mHashMap = new HashMap<>();
        mHashMap.put(KEY_CLASS, TestRotateImageViewActivity.class.getName());
        mHashMap.put(KEY_SIMPLE_NAME, TestRotateImageViewActivity.class.getSimpleName());
        mDatas.add(mHashMap);

        mHashMap = new HashMap<>();
        mHashMap.put(KEY_CLASS, TestTalkBackActivity.class.getName());
        mHashMap.put(KEY_SIMPLE_NAME, TestTalkBackActivity.class.getSimpleName());
        mDatas.add(mHashMap);

        mHashMap = new HashMap<>();
        mHashMap.put(KEY_CLASS, TestRbtnActivity.class.getName());
        mHashMap.put(KEY_SIMPLE_NAME, TestRbtnActivity.class.getSimpleName());
        mDatas.add(mHashMap);


        SimpleAdapter adapter = new SimpleAdapter(this, mDatas, android.R.layout.simple_list_item_1, new String[]{KEY_SIMPLE_NAME}, new int[]{android.R.id.text1});
        //3.绑定
        setListAdapter(adapter);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String s = mDatas.get(position).get(KEY_CLASS);
        try {
            start(this, Class.forName(s));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void start(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        BroadcaseReceiverFactory.unRegisterNetworkReceiver(this);
    }

    @Override
    public void onNetChange(int netType) {
        Log.e(TAG, "onNetChange: " + netType);
        if (netType != NetworkUtils.NETWORK_NO) {
            SocketClient.getInstance().connectToServer(SOCKET_SERVER_URL);
        }
    }
}
