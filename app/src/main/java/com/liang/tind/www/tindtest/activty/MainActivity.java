package com.liang.tind.www.tindtest.activty;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.liang.tind.www.tindtest.activty.kotlin.CoroutineActivity;
import com.liang.tind.www.tindtest.activty.kotlin.KotlinActivity;
import com.liang.tind.www.tindtest.activty.room.RoomActivity;
import com.liang.tind.www.tindtest.activty.widget.TestTabLayout;
import com.liang.tind.www.tindtest.activty.widget.TestViewActivity;
import com.liang.tind.www.tindtest.receiver.NetworkBroadcastReceiver;
import com.liang.tind.www.tindtest.util.NetworkUtils;
import com.liang.tind.www.tindtest.util.SocketClient;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import androidx.annotation.VisibleForTesting;

public class MainActivity extends ListActivity implements NetworkBroadcastReceiver.NetEvent {
  public static  transient final String KEY_CLASS = "class";
  public static final String KEY_SIMPLE_NAME = "simple_name";
  protected List<HashMap<String, String>> mDatas = new ArrayList<>();

  private static final String TAG = "MainActivity";
  public static final String SOCKET_SERVER_URL = "ws://47.96.31.242:7100";

  //    public static final String SOCKET_SERVER_URL = "ws://192.168.8.41:7100";
  @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
  public static final int test = 885;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //        BroadcaseReceiverFactory.registerNetworkReceiver(this, this);
    //        SocketClient.getInstance().connectToServer(SOCKET_SERVER_URL);
    SimpleAdapter adapter =
        new SimpleAdapter(
            this,
            mDatas,
            android.R.layout.simple_list_item_1,
            new String[] {KEY_SIMPLE_NAME},
            new int[] {android.R.id.text1});
    quickTest();
    initData();
    setListAdapter(adapter);
  }

  protected void initData() {
    // 1.数据源
    addItem(TestSocketActivity.class);
    addItem(TestChromiumActivity.class);
    addItem(TestHRFace.class);
    addItem(TestBuglyAndResProguad.class);

    addItem(TestTalkBackActivity.class);

    addItem(TestViewActivity.class);
    addItem(TestRxjavaActivity.class);
    addItem(EmptyActivity.class);
    addItem(TestTabLayout.class);
    addItem(KotlinActivity.class);
    addItem(RoomActivity.class);
    addItem(CoroutineActivity.class);
  }

  protected void addItem(Class claszz) {
    HashMap<String, String> hashMap;
    hashMap = new HashMap<>();
    hashMap.put(KEY_CLASS, claszz.getName());
    hashMap.put(KEY_SIMPLE_NAME, claszz.getSimpleName());
    mDatas.add(hashMap);
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

  private void quickTest() {
    long time = System.currentTimeMillis();
    Formatter f = new Formatter(new StringBuilder(50), Locale.US);
    int flags =
        DateUtils.FORMAT_SHOW_WEEKDAY
            | DateUtils.FORMAT_ABBREV_WEEKDAY
            | DateUtils.FORMAT_SHOW_DATE
            | DateUtils.FORMAT_SHOW_YEAR
            | DateUtils.FORMAT_NUMERIC_DATE
            |DateUtils.FORMAT_SHOW_TIME;
    String s = DateUtils.formatDateRange(this, f, time, time, flags).toString();
    Log.i(TAG, "quickTest(MainActivity.java:125): format time="+s);
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
  }

}
