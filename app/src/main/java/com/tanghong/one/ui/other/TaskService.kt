package com.tanghong.one.ui.other

import android.app.IntentService
import android.content.Intent

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/06
 *     desc   :
 *     version: 1.0
 * </pre>
 * 1.IntentService是Service的子类，是一个异步的，会自动停止的服务，很好解决了传统的Service中处理完耗时操作忘记停止并销毁Service的问题
 * 2.生成一个默认的且与线程相互独立的工作线程执行所有发送到onStartCommand()方法的Intent,可以在onHandleIntent()中处理
 * 3.串行队列,每次只运行一个任务,不存在线程安全问题,所有任务执行完后自动停止服务,不需要自己手动调用stopSelf()来停止
 */
class TaskService(val name: String) : IntentService(name) {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(intent: Intent?) {

    }
}