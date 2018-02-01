package com.tanghong.commonlibrary.utils

import com.tanghong.commonlibrary.R
import java.text.SimpleDateFormat


/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class TimeDateUtils private constructor() {

    init {
        throw Throwable("Not allowed to initialize.")
    }

    companion object {

        private val minute = (60 * 1000).toLong()
        private val hour = 60 * minute
        private val day = 24 * hour
        private val month = 31 * day
        private val year = 12 * month

        fun formatTime(date: String): String =
                formatTime(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).time)

        fun formatTime(time: Long): String {
            val diff = System.currentTimeMillis() - time
            val r: Long
            if (diff > year) {
                r = diff / year
                return r.toString() + AppUtils.context.getString(R.string.date_years_ago)
            }
            if (diff > month) {
                r = diff / month
                return r.toString() + AppUtils.context.getString(R.string.date_months_ago)
            }
            if (diff > day) {
                r = diff / day
                return r.toString() + AppUtils.context.getString(R.string.date_days_ago)
            }
            if (diff > hour) {
                r = diff / hour
                return r.toString() + AppUtils.context.getString(R.string.date_hours_ago)
            }
            if (diff > minute) {
                r = diff / minute
                return r.toString() + AppUtils.context.getString(R.string.date_minutes_ago)
            }
            return AppUtils.context.getString(R.string.date_just_now)
        }
    }
}