/*
 * Copyright (C) 2007 The Android  Source Project
 *
 * Licensed under the RichenInfo License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.richeninfo.com/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liang.tind.www.tindtest.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;

import java.util.UUID;

public class IdentifyUtils {

    private static String identify = null;

    /**
     * wenJun 获取设备唯一标识符 2013-4-23
     *
     * @param context
     * @return
     */
    public static String getIdentify(Context context) {
        if (identify == null) {
            identify = getNotNullId(context);
//            SPUtils.put(context, BaseConstants.IntentExtras.KEY_IDENTIFY, identify);
        }
        return identify;
    }

    /**
     * @param context
     * @return
     */
    private static String createAndWriteIdentify(Context context) {
        return UUID.randomUUID().toString();
    }

    /**
     * wenJun 获取一个非空的 唯一标识符( mac, imei, 串 ) 2013-4-23
     *
     * @param context
     * @return
     */
    private static String getNotNullId(Context context) {
//        String localId = (String) SPUtils.get(context, BaseConstants.IntentExtras.KEY_IDENTIFY, null);
        String localId=null;
        String macAddress = getMacAddress(context);
        if (localId != null) {
            return localId;
        } else if (macAddress != null) {
            return macAddress;
        } else {
            localId = getUniqueId(context);
            return localId;
        }
    }


    public static String getUniqueId(Context context) {
        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;

        return MD5Utils.getMD5Str(id);
    }

    /**
     * wenJun 获取手机 MAC 号 2013-4-23
     *
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifi != null) {
            WifiInfo info = wifi.getConnectionInfo();

            return info.getMacAddress();
        } else {
            return null;
        }

    }



}
