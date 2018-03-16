package com.tanghong.one.service

import android.app.IntentService
import android.content.Intent

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/15
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * IntentService：
 * 它本质是一种特殊的Service,继承自Service并且本身就是一个抽象类
 * 它可以用于在后台执行耗时的异步任务，当任务完成后会自动停止
 * 它拥有较高的优先级，不易被系统杀死（继承自Service的缘故），因此比较适合执行一些高优先级的异步任务
 * 它内部通过HandlerThread和Handler实现异步操作
 * 创建IntentService时，只需实现onHandleIntent和构造方法，onHandleIntent为异步方法，可以执行耗时操作
 *
 * HandlerThread:
 * HandlerThread本质上是一个线程类，它继承了Thread；
 * HandlerThread有自己的内部Looper对象，可以进行looper循环；
 * 通过获取HandlerThread的looper对象传递给Handler对象，可以在handleMessage方法中执行异步任务。
 * 创建HandlerThread后必须先调用HandlerThread.start()方法，Thread会先调用run方法，创建Looper对象。
 */
class AsyncService(val name: String) : IntentService(name) {

    override fun onHandleIntent(intent: Intent?) {

    }
}