package com.liang.tind.www.tindtest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.liang.tind.www.tindtest.IMyAidlInterface;

import androidx.annotation.Nullable;

/**
 * @author 梁天德
 * @date 2020/01/07 14:50
 * desc
 */
public class RemoteService extends Service {
    private static final String TAG = "RemoteService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }

    IMyAidlInterface.Stub mStub = new IMyAidlInterface.Stub() {
        @Override
        public void test(int value) throws RemoteException {
            Log.i(TAG, "test(RemoteService.java:28): "+value);
        }
    };
}
