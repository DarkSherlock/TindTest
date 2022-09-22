package com.liang.tind.www.tindtest.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log


/**
 *@author lonnie.liang
 *@date 2021/03/15 17:46
 *
 */
class BatteryChangedReceiver : BroadcastReceiver() {
    private var currentBatteryInfo: BatteryInfo? = null

    override fun onReceive(context: Context, intent: Intent) {
        val batteryInfo = getBatteryInfo(intent)
        if (currentBatteryInfo != batteryInfo) {
            onStatusChange(batteryInfo)
        }
    }

    private fun onStatusChange(batteryInfo: BatteryInfo) {
        currentBatteryInfo = batteryInfo
        /**
         * when the iPad was in charging -> inCharging;
         * when the iPad was in charging and full -> full;
         * when the ipad was not in charging & battery level < 20% -> low;
         * other case -> fine;
         */
        val status = if (batteryInfo.isCharging) {
            if (batteryInfo.isFull) {
                "full"
            } else {
                "isCharging"
            }
        } else {
            if (batteryInfo.level < BATTERY_LOW_LEVEL) {
                "low"
            } else {
                "fine"
            }
        }
        Log.i(TAG, "onStatusChange: $status")
    }

    private fun getBatteryInfo(intent: Intent): BatteryInfo {
        val level = BATTERY_FULL_SCALE * intent.getIntExtra(
            BatteryManager.EXTRA_LEVEL,
            0
        ) / intent.getIntExtra(BatteryManager.EXTRA_SCALE, BATTERY_FULL_SCALE)

        val status = intent.getIntExtra(
            BatteryManager.EXTRA_STATUS,
            BatteryManager.BATTERY_STATUS_UNKNOWN
        )
        val charged = status == BatteryManager.BATTERY_STATUS_FULL
        val charging = status == BatteryManager.BATTERY_STATUS_CHARGING
        val batteryInfo = BatteryInfo(level, charging, charged)
        Log.i(TAG, "${getStatusDescriber(status)}: $batteryInfo")
        return batteryInfo
    }

    private fun getStatusDescriber(status: Int): String {
        return when (status) {
            BatteryManager.BATTERY_STATUS_FULL -> "BATTERY_STATUS_FULL"
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "BATTERY_STATUS_NOT_CHARGING"
            BatteryManager.BATTERY_STATUS_DISCHARGING -> "BATTERY_STATUS_DISCHARGING"
            BatteryManager.BATTERY_STATUS_CHARGING -> "BATTERY_STATUS_CHARGING"
            else -> "BATTERY_STATUS_UNKNOWN"
        }
    }

    data class BatteryInfo(val level: Int, val isCharging: Boolean, val isFull: Boolean)

    companion object {
        private const val TAG = "BatteryChangedReceiver"
        private const val BATTERY_FULL_SCALE = 100
        private const val BATTERY_LOW_LEVEL = 20
    }
}