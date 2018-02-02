package com.tanghong.commonlibrary.utils

import android.content.Context
import android.content.pm.PackageManager
import kotlin.properties.Delegates

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class AppUtils private constructor() {

    init {
        throw Throwable("Not allowed to initialize.")
    }

    companion object {

        var context: Context by Delegates.notNull<Context>()

        fun init(context: Context) {
            this.context = context
        }

        /**
         * 得到软件版本号
         *
         * @param context 上下文
         * @return 当前版本Code
         */
        fun getVerCode(): Int {
            var verCode = -1
            try {
                val packageName = context.packageName
                verCode = context.packageManager.getPackageInfo(packageName, 0).versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return verCode
        }

        /**
         * 得到软件显示版本信息
         *
         * @param context 上下文
         * @return 当前版本信息
         */
        fun getVerName(): String {
            var verName = ""
            try {
                val packageName = context.packageName
                verName = context.packageManager.getPackageInfo(packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return verName
        }

        /**
         * 获取手机系统SDK版本
         *
         * @return 如API 17 则返回 17
         */
        val sdkVersion: Int
            get() = android.os.Build.VERSION.SDK_INT
    }
}