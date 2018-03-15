package com.tanghong.one.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
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

    val map = LinkedHashMap<String, Any>()

    override fun onStopJob(params: JobParameters?): Boolean {

        return false
    }

    override fun onStartJob(params: JobParameters?): Boolean {

        return false
    }
}