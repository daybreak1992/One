package com.tanghong.one.service

import android.annotation.SuppressLint
import android.app.Service
import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class JobSchedulerService : JobService() {

    val msg_what = 1
    val job_id = 1
    val handler = @SuppressLint("HandlerLeak")
    object : Handler() {

        override fun handleMessage(msg: Message?) {
            val params = msg?.obj
            if (params is JobParameters) {
                jobFinished(params, true)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        scheduleJob()
        return Service.START_STICKY
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        val message = Message.obtain()
        message.what = msg_what
        message.obj = params
        handler.sendMessage(message)
        //返回false表示执行完毕，返回true表示需要开发者自己调用jobFinished方法通知系统已执行完成
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        //停止，不是结束。jobFinished不会直接触发onStopJob
        //必须在“onStartJob之后，jobFinished之前”取消任务，才会在jobFinished之后触发onStopJob
        handler.removeMessages(msg_what)
        return true
    }

    private fun scheduleJob() {
        val jobInfo = JobInfo.Builder(job_id, ComponentName(this, JobSchedulerService::class.java))
                //设置需要的网络条件，默认为JobInfo.NETWORK_TYPE_NONE即无网络时执行
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE)
                //是否在充电时执行
                .setRequiresCharging(false)
                //是否在空闲时执行
                .setRequiresDeviceIdle(false)
                //重启后是否还要继续执行，此时需要声明权限RECEIVE_BOOT_COMPLETED
                //否则会报错“java.lang.IllegalArgumentException: Error: requested job be persisted without holding RECEIVE_BOOT_COMPLETED permission.”
                //而且RECEIVE_BOOT_COMPLETED需要在安装的时候就要声明，如果一开始没声明，在升级时才声明，那么依然会报权限不足的错误
                .setPersisted(true)
                //设置时间间隔，单位毫秒
                .setPeriodic(3000)
                .build()

        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }
}