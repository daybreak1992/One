package com.tanghong.one.service

import android.app.job.JobScheduler
import android.content.Context
import android.os.Build

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class JobSchedulerManager {

    fun createJobScheduler(context: Context) {
        val jobScheduler: JobScheduler? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        } else {
            null
        }
    }
}