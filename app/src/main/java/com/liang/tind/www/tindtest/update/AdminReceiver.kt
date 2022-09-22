package com.liang.tind.www.tindtest.update

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 *@author lonnie.liang
 *@date 2021/03/11 17:47
 *
 */
class AdminReceiver : DeviceAdminReceiver() {

    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Log.d(TAG, "Device Owner Enabled")
    }

    companion object {
        private const val TAG = "AdminReceiver"
    }
}