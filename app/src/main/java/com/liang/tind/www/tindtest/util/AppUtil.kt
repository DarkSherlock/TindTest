package com.liang.tind.www.tindtest.util

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInstaller
import android.content.pm.PackageInstaller.SessionParams
import android.util.Log
import java.io.*
import java.lang.reflect.Method


/**
 *@author lonnie.liang
 *@date 2021/03/11 17:19
 *
 */
object AppUtil {
    private const val TAG = "AppUtil"

    fun install(context: Context, packageName: String, apkPath: String) {

        // PackageManager provides an instance of PackageInstaller
        val packageInstaller = context.packageManager.packageInstaller

        // Prepare params for installing one APK file with MODE_FULL_INSTALL
        // We could use MODE_INHERIT_EXISTING to install multiple split APKs
        val params =
            PackageInstaller.SessionParams(PackageInstaller.SessionParams.MODE_FULL_INSTALL)
        params.setAppPackageName(packageName)

        // Get a PackageInstaller.Session for performing the actual update
        val sessionId = packageInstaller.createSession(params)
        val session = packageInstaller.openSession(sessionId)

        // Copy APK file bytes into OutputStream provided by install Session
        val unknownBytesLength = -1L
        val out = session.openWrite(packageName, 0, unknownBytesLength)
        val fis = File(apkPath).inputStream()
        fis.copyTo(out)
        session.fsync(out)
        out.close()
        fis.close()

        // The app gets killed after installation session commit
        session.commit(
            PendingIntent.getBroadcast(
                context,
                sessionId,
                Intent("android.intent.action.MAIN"),
                0
            ).intentSender
        )
    }


    fun install(context: Context, apkPath: String): Boolean {
        val packageInstaller = context.packageManager.packageInstaller
        val params = SessionParams(SessionParams.MODE_FULL_INSTALL)
        val pkgName = getApkPackageName(context, apkPath) ?: return false
        params.setAppPackageName(pkgName)
        try {
            val allowDowngrade: Method =
                SessionParams::class.java.getMethod(
                    "setAllowDowngrade",
                    Boolean::class.javaPrimitiveType
                )
            allowDowngrade.isAccessible = true
            allowDowngrade.invoke(params, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var os: OutputStream? = null
        var inputStream: InputStream? = null

        try {
            val sessionId = packageInstaller.createSession(params)
            val session = packageInstaller.openSession(sessionId)
            os = session.openWrite(pkgName, 0, -1)
            inputStream = FileInputStream(apkPath)
            val buffer = ByteArray(1024)
            var len: Int
            while (inputStream.read(buffer).also { len = it } != -1) {
                os.write(buffer, 0, len)
            }
            session.fsync(os)
            os.close()
            os = null
            inputStream.close()
            inputStream = null
            session.commit(
                PendingIntent.getBroadcast(
                    context, sessionId,
                    Intent(Intent.ACTION_MAIN), 0
                ).intentSender
            )
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            return false
        } finally {
            if (os != null) {
                try {
                    os.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return true
    }

    /**
     * 获取apk的包名
     */
    fun getApkPackageName(context: Context, apkPath: String): String? {
        val pm = context.packageManager
        val info = pm.getPackageArchiveInfo(apkPath, 0)
        return info?.packageName
    }
}