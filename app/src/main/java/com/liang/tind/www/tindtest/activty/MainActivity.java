package com.liang.tind.www.tindtest.activty;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.math.BigDecimal;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.activty.dump.DumpActivity;
import com.liang.tind.www.tindtest.activty.kotlin.CoroutineActivity;
import com.liang.tind.www.tindtest.activty.kotlin.KotlinActivity;
import com.liang.tind.www.tindtest.activty.room.RoomActivity;
import com.liang.tind.www.tindtest.activty.widget.TestTabLayout;
import com.liang.tind.www.tindtest.activty.widget.TestViewActivity;
import com.liang.tind.www.tindtest.battery.BatteryChangedReceiver;
import com.liang.tind.www.tindtest.receiver.NetworkBroadcastReceiver;
import com.liang.tind.www.tindtest.util.NetworkUtils;
import com.liang.tind.www.tindtest.util.SocketClient;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends ListActivity implements NetworkBroadcastReceiver.NetEvent {
    public static transient final String KEY_CLASS = "class";
    public static final String KEY_SIMPLE_NAME = "simple_name";
    protected List<HashMap<String, String>> mDatas = new ArrayList<>();

    private static final String TAG = "MainActivity";
    public static final String SOCKET_SERVER_URL = "ws://47.96.31.242:7100";

    private BatteryChangedReceiver mBatteryChangedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Screen info:" + getResources().getDisplayMetrics().toString());
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                mDatas,
                android.R.layout.simple_list_item_1,
                new String[]{KEY_SIMPLE_NAME},
                new int[]{android.R.id.text1}
        );
        quickTest();
        initData();
        setListAdapter(adapter);
        mBatteryChangedReceiver = new BatteryChangedReceiver();
        registerReceiver(mBatteryChangedReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    protected void initData() {
        // 1.数据源
        addItem(TestViewActivity.class);
        addItem(TestSocketActivity.class);
        addItem(TestChromiumActivity.class);
        addItem(TestBuglyAndResProguad.class);

        addItem(TestTalkBackActivity.class);


        addItem(TestRxjavaActivity.class);
        addItem(GenericsActivity.class);
        addItem(TestTabLayout.class);
        addItem(KotlinActivity.class);
        addItem(RoomActivity.class);
        addItem(CoroutineActivity.class);
        addItem(DumpActivity.class);
        addItem(BtnActivity.class);
    }

    protected <T> void addItem(Class<T> clazz) {
        HashMap<String, String> hashMap;
        hashMap = new HashMap<>();
        hashMap.put(KEY_CLASS, clazz.getName());
        hashMap.put(KEY_SIMPLE_NAME, clazz.getSimpleName());
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
    public void onNetChange(int netType) {
        Log.e(TAG, "onNetChange: " + netType);
        if (netType != NetworkUtils.NETWORK_NO) {
            SocketClient.getInstance().connectToServer(SOCKET_SERVER_URL);
        }
    }

    private void quickTest() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("lonnir");
        arrayList.add("tind");
        long time = System.currentTimeMillis();
        Formatter f = new Formatter(new StringBuilder(50), Locale.US);
        int flags =
                DateUtils.FORMAT_SHOW_WEEKDAY
                        | DateUtils.FORMAT_ABBREV_WEEKDAY
                        | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_NUMERIC_DATE
                        | DateUtils.FORMAT_SHOW_TIME;
        String s = DateUtils.formatDateRange(this, f, time, time, flags).toString();
        Log.i(TAG, "quickTest(MainActivity.java:125): format time=" + s);
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
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.voice_command_didnt_catch_help);
        Log.i(TAG, "uri(): " + uri.toString());

        Log.i(TAG, "filesDir: " + getFilesDir());
        Log.i(TAG, "getCacheDir: " + getCacheDir());
        Log.i(TAG, "getExternalFilesDir: " + getExternalFilesDir(null));
        Log.i(TAG, "getExternalCacheDir: " + getExternalCacheDir());
//        for (File file : getFilesDir().listFiles()) {
//            Log.i(TAG, "file: "+file);
//            Log.i(TAG, "file.exists: "+file.exists());
//        }
//        FileUtil.saveLog("test", getCacheDir().toString());
//        String filePath = "/data/data/com.liang.tind.www.tindtest/cache/2021-09-07 14:37:09.txt";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            String uriPath = Uri.fromFile(new File(filePath)).toString();
//            Log.i(TAG, "uriPath(): " + uriPath);
//            Path path = Paths.get(URI.create(uriPath));
//            try {
//                Files.delete(path);
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        }

        WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        Log.i(TAG, "wifiInfo(): " + wifiInfo.getMacAddress());

        long lastRebootTime = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        String date = DateUtils.formatDateTime(this, lastRebootTime, DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME);
        Log.i(TAG, "last reboot time:" + date);
        logDouble(0.33333);
        logDouble(0.0000);
        logDouble(0.349999);
        Log.i(TAG, "quickTest(): " + (1 << 30));
        Log.i(TAG, "quickTest(): " + (1 << 31));
        Log.i(TAG, "quickTest(): " + (1 << 32));
        Log.i(TAG, "quickTest(): " + (1 << 33));
        Log.i(TAG, "quickTest(): " + (Integer.MAX_VALUE));
    }


    public void logDouble(double number) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Log.i(TAG, "origin: " + number);
            BigDecimal bg = new BigDecimal(number).setScale(2, BigDecimal.ROUND_HALF_UP);
            float result = bg.floatValue();
            Log.i(TAG, "result:" + result);
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy(): ");
        super.onDestroy();
        unregisterReceiver(mBatteryChangedReceiver);
    }

    public static boolean hasPermission(Context context, String permission) {
        return context.checkCallingOrSelfPermission(permission) == PERMISSION_GRANTED;
    }

}
