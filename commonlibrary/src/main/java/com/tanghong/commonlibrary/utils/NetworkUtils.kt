package com.tanghong.commonlibrary.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager
import java.net.NetworkInterface
import java.net.SocketException

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class NetworkUtils private constructor() {

    init {
        throw Throwable("Not allowed to initialize.")
    }

    companion object {

        /**
         * 监测网络状态
         *
         * @param context
         * @return
         */
        @JvmStatic
        fun isNetworkAvailable(context: Context): Boolean {
            val manager = context.applicationContext.getSystemService(
                    Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return !(null == info || !info.isAvailable)
        }

        /**
         * 得到ip地址
         *
         * @return
         */
        @JvmStatic
        fun getLocalIpAddress(): String {
            var ret = ""
            try {
                val en = NetworkInterface.getNetworkInterfaces()
                while (en.hasMoreElements()) {
                    val enumIpAddress = en.nextElement().inetAddresses
                    while (enumIpAddress.hasMoreElements()) {
                        val netAddress = enumIpAddress.nextElement()
                        if (!netAddress.isLoopbackAddress) {
                            ret = netAddress.hostAddress.toString()
                        }
                    }
                }
            } catch (ex: SocketException) {
                ex.printStackTrace()
            }

            return ret
        }

        /**
         * 是否为wifi
         *
         * @param context
         * @return boolean
         */
        @JvmStatic
        fun isWifi(context: Context): Boolean {
            val connectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetInfo = connectivityManager.activeNetworkInfo
            return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI
        }

        /**
         * wifi是否能用
         */
        @JvmStatic
        fun isWifiEnabled(context: Context): Boolean {
            val mgrConn = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mgrTel = context
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return mgrConn.activeNetworkInfo != null && mgrConn
                    .activeNetworkInfo.state == NetworkInfo.State.CONNECTED || mgrTel
                    .networkType == TelephonyManager.NETWORK_TYPE_UMTS
        }
    }
}