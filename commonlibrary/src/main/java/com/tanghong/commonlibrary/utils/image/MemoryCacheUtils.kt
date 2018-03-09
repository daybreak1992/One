package com.tanghong.commonlibrary.utils.image

import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
import com.tanghong.commonlibrary.utils.EncodeUtils

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class MemoryCacheUtils {
    //1.因为强引用,容易造成内存溢出，所以考虑使用下面弱引用的方法
//    private var mMemoryCache: HashMap<String, Bitmap>? = null
    //2.因为在Android2.3+后,系统会优先考虑回收弱引用对象,官方提出使用LruCache,使用least recentlly use最少最近使用算法
//    private var mMemoryCache: HashMap<String, SoftReference<Bitmap>>? = null
    private var mMemoryCache: LruCache<String, Bitmap>? = null

    fun getBitmapFromMemory(url: String): Bitmap? {
        createMemoryCache()
        Log.i("image", "getBitmapFromMemory = ${mMemoryCache?.size()}")
        return mMemoryCache?.get(EncodeUtils.urlEncode(url))
    }

    fun setBitmapToMemory(url: String, bit: Bitmap) {
        createMemoryCache()
        mMemoryCache?.put(EncodeUtils.urlEncode(url), bit)
        Log.i("image", "setBitmapToMemory = ${mMemoryCache?.size()}")
    }

    private fun createMemoryCache() {
        if (mMemoryCache == null) {
            synchronized(MemoryCacheUtils::class) {
                if (mMemoryCache == null) {
                    //得到手机最大允许内存的1/8,即超过指定内存,则开始回收
                    val maxMemory = Runtime.getRuntime().maxMemory() / 8
                    mMemoryCache = object : LruCache<String, Bitmap>(maxMemory.toInt()) {

                        override fun sizeOf(key: String?, value: Bitmap?): Int {
                            value?.let {
                                Log.i("image", "byteCount = ${it.byteCount}")
                                return it.byteCount
                            }
                            return 0
                        }
                    }
                }
            }
        }
    }
}