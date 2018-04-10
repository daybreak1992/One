package http

import com.tanghong.commonlibrary.utils.NetworkUtils
import com.tanghong.one.app.App
import com.tanghong.one.app.Constants
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object RetrofitManager {

    private var retrofit: Retrofit? = null

    val api: Api by lazy {
        createRetrofit()!!.create(Api::class.java)
    }

    private fun createRetrofit(): Retrofit? {
        if (retrofit == null) {
            val client: OkHttpClient = creteClient()
            retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(Constants.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit
    }

    private fun creteClient(): OkHttpClient {
        //设置 请求的缓存的大小跟位置
        val cacheFile = File(App.appContext.cacheDir, "cache")
        val cache = Cache(cacheFile, 1024 * 1024 * 50) //50Mb 缓存的大小
        val builder = OkHttpClient.Builder()
        if (Constants.DEBUG) {
            //添加一个log拦截器,打印所有的log
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            //可以设置请求过滤的水平,body,basic,headers
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
        }
        builder.addInterceptor(addQueryParameterInterceptor())  //参数添加
                .addInterceptor(addHeaderInterceptor()) // token过滤
//                .addInterceptor(addCacheInterceptor())
                .cache(cache)  //添加缓存
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
        return builder.build()
    }

    /**
     * 设置公共参数
     */
    private fun addQueryParameterInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request: Request
            val modifiedUrl = originalRequest.url().newBuilder()
                    // Provide your custom parameter here
                    .addQueryParameter("user_id", App.user?.user_id)
                    .addQueryParameter("sign", Constants.sign)
                    .addQueryParameter("uuid", Constants.uuid)
                    .addQueryParameter("channel", Constants.channel)
                    .addQueryParameter("version", Constants.version)
                    .addQueryParameter("platform", Constants.platform)
                    .build()
            request = originalRequest.newBuilder().url(modifiedUrl).build()
            chain.proceed(request)
        }
    }

    /**
     * 设置头
     */
    private fun addHeaderInterceptor(): Interceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
                // Provide your custom header here
//                .header("access-control-allow-origin", "*")
//                .header("cache-control", "no-cache")
//                .header("connection", "keep-alive")
//                .header("content-encoding", "gzip")
//                .header("content-type", "application/json")
//                .header("date", Date().toGMTString())
//                .header("server", "nginx")
//                .header("transfer-encoding", "chunked")
//                .header("x-powered-by", "PHP/5.6.24")
                .method(originalRequest.method(), originalRequest.body())
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    /**
     * 设置缓存
     */
    private fun addCacheInterceptor(): Interceptor = Interceptor { chain ->
        var request = chain.request()
        if (!NetworkUtils.isNetworkAvailable(App.appContext)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }
        val response = chain.proceed(request)
        if (NetworkUtils.isNetworkAvailable(App.appContext)) {
            val maxAge = 0
            // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build()
        } else {
            // 无网络时，设置超时为4周  只对get有用,post没有缓冲
            val maxStale = 60 * 60 * 24 * 28
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("nyn")
                    .build()
        }
        response
    }
}
