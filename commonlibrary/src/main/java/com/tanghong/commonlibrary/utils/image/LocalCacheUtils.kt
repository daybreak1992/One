package com.tanghong.commonlibrary.utils.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import com.tanghong.commonlibrary.utils.EncodeUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class LocalCacheUtils {
    private val cache_path = Environment.getExternalStorageDirectory().absolutePath + "/image_cache"

    fun getBitmapFromLocal(url: String): Bitmap? {
        var fileName: String? = null
        try {
            fileName = EncodeUtils.urlEncode(url)
            val file = File(cache_path, fileName)
            return BitmapFactory.decodeStream(FileInputStream(file))
        } catch (e: Exception) {
            Log.i("image", "e = ${e.toString()}")
        }
        return null
    }

    fun setBitmapToLocal(url: String, bit: Bitmap) {
        try {
            val fileName = EncodeUtils.urlEncode(url)
            val file = File(cache_path, fileName)
            val parentFile = file.parentFile
            if (!parentFile.exists()) {
                parentFile.mkdirs()
            }
            bit.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(file))
        } catch (e: Exception) {
            Log.i("image", "e = ${e.toString()}")
        }
    }
}