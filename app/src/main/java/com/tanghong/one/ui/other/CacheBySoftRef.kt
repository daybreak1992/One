package com.tanghong.one.ui.other

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.lang.ref.SoftReference

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CacheBySoftRef {

    private val imageCache = hashMapOf<String, SoftReference<Bitmap>>()

    fun addBitmapToCache(path: String) {
        val bitmap = BitmapFactory.decodeFile(path)
        val softReference = SoftReference<Bitmap>(bitmap)
        imageCache[path] = softReference
    }

    fun getBitmapByPath(path: String): Bitmap? {
        val softReference = imageCache[path] ?: return null
        return softReference.get()
    }
}