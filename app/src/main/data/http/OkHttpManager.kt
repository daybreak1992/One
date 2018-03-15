package http

import com.tanghong.one.app.App
import com.tanghong.one.app.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object OkHttpManager {

    private var client: OkHttpClient? = null

    fun instance(): OkHttpClient {
        if (client == null) {
            synchronized(OkHttpManager::class) {
                if (client == null) {
                    client = createClient()
                }
            }
        }
        return client!!
    }

    private fun createClient(): OkHttpClient = OkHttpClient()
            .newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS) //连接超时时间
            .readTimeout(10, TimeUnit.SECONDS) //读取超时时间
            .writeTimeout(10, TimeUnit.SECONDS) //写入超时时间
            .addInterceptor(addQueryParameterInterceptor())
            .addInterceptor(addHeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

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


}