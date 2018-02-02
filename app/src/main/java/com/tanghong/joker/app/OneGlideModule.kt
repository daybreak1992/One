package com.tanghong.joker.app

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import java.io.InputStream


/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/02
 *     desc   :
 *     version: 1.0
 *
 *     DiskCacheStrategy.NONE 不做磁盘缓存
 *     DiskCacheStrategy.SOURCE 只缓存图像原图
 *     DiskCacheStrategy.RESULT 只缓存加载后的图像，即处理后最终显示时的图像
 *     DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
 * </pre>
 */
@GlideModule
class OneGlideModule : AppGlideModule() {

    val memoryCacheSize: Long = 20 * 1024 * 1024
    val diskCacheSize: Long = 100 * 1024 * 1024
    val diskCacheName = "one_image_cache"
    val options: RequestOptions = RequestOptions()
            .format(DecodeFormat.PREFER_RGB_565)

    /**
     * isManifestParsingEnabled 设置清单解析，设置为false，避免添加相同的modules两次
     */
    override fun isManifestParsingEnabled(): Boolean = false

    /**
     * 配置glide
     */
    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        builder?.setDefaultRequestOptions(options)
        //MemorySizeCalculator类通过考虑设备给定的可用内存和屏幕大小想出合理的默认大小，通过LruResourceCache进行缓存。
        val calculator: MemorySizeCalculator
                = MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(2f)
                .build()
        builder?.setMemoryCache(LruResourceCache(calculator.memoryCacheSize.toLong()))
        //自定义缓存大小
//        builder?.setMemoryCache(LruResourceCache(memoryCacheSize))
        //Disk Cache 自定义内置磁盘缓存大小并指定路径
        builder?.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheName, diskCacheSize))
        //Disk Cache 自定义外置磁盘缓存大小并指定路径
//        builder?.setDiskCache(ExternalPreferredCacheDiskCacheFactory(context, diskCacheName, diskCacheSize))
    }

    /**
     * 将图片加载的网络请求换为OkHttp
     */
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())
    }
}