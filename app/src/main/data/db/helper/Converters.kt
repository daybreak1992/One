package db.helper

import android.arch.persistence.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class Converters {

    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)

    /**
     * 时间戳转日期
     */
    @TypeConverter
    fun fromTimestamp(value: String?): Date {
        if (value == null) {
            return Date(System.currentTimeMillis())
        }
        synchronized(format) {
            return format.parse(value)
        }
    }

    /**
     * 日期转时间
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): String {
        if (date == null) {
            val nowDate = Date(System.currentTimeMillis())
            synchronized(format) {
                return format.format(nowDate)
            }
        }
        synchronized(format) {
            return format.format(date)
        }
    }
}