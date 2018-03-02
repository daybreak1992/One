package com.tanghong.one.app

import android.app.Application
import android.content.Context
import android.util.Log
import com.squareup.leakcanary.LeakCanary
import com.tanghong.commonlibrary.utils.AppUtils
import com.tanghong.commonlibrary.utils.RxUtils
import com.tencent.smtt.sdk.QbSdk
import db.helper.DbHelper
import io.reactivex.Flowable
import io.reactivex.subscribers.ResourceSubscriber
import model.User
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

        var user: User? = null

        fun resetUser(resetUser: User?) {
            if (resetUser == null && user != null) {
                Flowable.just(user)
                        .map { t: User ->
                            DbHelper.getUserDao().delete(t)
                        }.compose(RxUtils.composeIo())
                        .subscribeWith(object : ResourceSubscriber<Int>() {
                            override fun onComplete() {
                                Log.i("main", "onComplete111 = $isDisposed")
                                dispose()
                                Log.i("main", "onComplete222 = $isDisposed")
                            }

                            override fun onNext(t: Int?) {
                                Log.i("main", "onNext = $t")
                            }

                            override fun onError(t: Throwable?) {
                                Log.i("main", "onError = ${t?.message}")
                            }
                        })
            }
            user = resetUser
        }

        fun isLogin(): Boolean = user != null
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        appContext = applicationContext
        //初始化工具类
        AppUtils.init(this)
        //初始化数据库
        DbHelper.initDb(appContext)
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