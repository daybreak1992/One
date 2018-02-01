package com.tanghong.joker.app

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import com.tanghong.commonlibrary.utils.AppUtils
import com.tencent.smtt.sdk.QbSdk
import kotlin.properties.Delegates

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class App : Application() {

    companion object {

        var app: App by Delegates.notNull<App>()
            private set

        var appContext: Context by Delegates.notNull<Context>()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        appContext = applicationContext
        //初始化工具类
        AppUtils.init(this)
        //初始化x5WebView
        QbSdk.initX5Environment(appContext, object : QbSdk.PreInitCallback {

            override fun onCoreInitFinished() {

            }

            override fun onViewInitFinished(b: Boolean) {

            }
        })
        //初始化LeakCanary
        initLeakCanary()
    }

    fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(appContext)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(app)
    }
}