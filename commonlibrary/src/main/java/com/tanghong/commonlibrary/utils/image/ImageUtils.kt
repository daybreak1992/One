package com.tanghong.commonlibrary.utils.image

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class ImageUtils {
    private var memoryCacheUtils: MemoryCacheUtils? = null
    private var localCacheUtils: LocalCacheUtils? = null
    private var netCacheUtils: NetCacheUtils? = null

    private var handler: Handler? = null

    init {
        memoryCacheUtils = MemoryCacheUtils()
        localCacheUtils = LocalCacheUtils()
        netCacheUtils = NetCacheUtils(localCacheUtils, memoryCacheUtils)

        handler = Handler(Looper.getMainLooper())
    }

    fun display(iv: ImageView, url: String) {
        var bitmap = memoryCacheUtils?.getBitmapFromMemory(url)
        if (bitmap != null) {
            iv.setImageBitmap(bitmap)
            Log.i("image", "display memory")
            return
        }
        bitmap = localCacheUtils?.getBitmapFromLocal(url)
        if (bitmap != null) {
            iv.setImageBitmap(bitmap)
            memoryCacheUtils?.setBitmapToMemory(url, bitmap)
            Log.i("image", "display local")
            return
        }
        netCacheUtils?.getBitmapFromNet(url, object : NetCacheUtils.OnBitmapCallback {
            override fun onSuccess(bit: Bitmap) {
                Log.i("image", "display net")
                memoryCacheUtils?.setBitmapToMemory(url, bit)
                localCacheUtils?.setBitmapToLocal(url, bit)
                handler?.post({
                    iv.setImageBitmap(bit)
                })
            }

            override fun onError(e: Exception) {
                Log.i("image", "display net e = ${e.toString()}")
            }
        })
    }
}