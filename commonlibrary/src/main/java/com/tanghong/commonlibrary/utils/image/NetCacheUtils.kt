package com.tanghong.commonlibrary.utils.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.*

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/08
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * corePoolSize  线程池中核心线程的数量
 * maximumPoolSize  线程池中最大线程数量
 * keepAliveTime 非核心线程的超时时长，当系统中非核心线程闲置时间超过keepAliveTime之后，则会被回收。
 * 如果ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true，则该参数也表示核心线程的超时时长
 * unit 第三个参数的单位，有纳秒、微秒、毫秒、秒、分、时、天等
 * workQueue 线程池中的任务队列，该队列主要用来存储已经被提交但是尚未执行的任务。存储在这里的任务是由ThreadPoolExecutor的execute方法提交来的。
 * threadFactory  为线程池提供创建新线程的功能，这个我们一般使用默认即可
 * handler 拒绝策略，当线程无法执行新任务时（一般是由于线程池中的线程数量已经达到最大数或者线程池关闭导致的），
 * 默认情况下，当线程池无法处理新线程时，会抛出一个RejectedExecutionException
 *
 * 1、shutdown()方法在终止前允许执行以前提交的任务。
 * 2、shutdownNow()方法则是阻止正在任务队列中等待任务的启动并试图停止当前正在执行的任务
 *
 * 1.ArrayBlockingQueue：这个表示一个规定了大小的BlockingQueue，ArrayBlockingQueue的构造函数接受一个int类型的数据，
 * 该数据表示BlockingQueue的大小，存储在ArrayBlockingQueue中的元素按照FIFO（先进先出）的方式来进行存取。
 * 2.LinkedBlockingQueue：这个表示一个大小不确定的BlockingQueue，在LinkedBlockingQueue的构造方法中可以传一个int
 * 类型的数据，这样创建出来的LinkedBlockingQueue是有大小的，也可以不传，不传的话，LinkedBlockingQueue的大小就为Integer.MAX_VALUE
 * 3.PriorityBlockingQueue：这个队列和LinkedBlockingQueue类似，不同的是PriorityBlockingQueue中的元素不是
 * 按照FIFO来排序的，而是按照元素的Comparator来决定存取顺序的（这个功能也反映了存入PriorityBlockingQueue中的数据必须实现了Comparator接口）
 * 4.SynchronousQueue：这个是同步Queue，属于线程安全的BlockingQueue的一种，在SynchronousQueue中，
 * 生产者线程的插入操作必须要等待消费者线程的移除操作，Synchronous内部没有数据缓存空间，因此我们无法对
 * SynchronousQueue进行读取或者遍历其中的数据，元素只有在你试图取走的时候才有可能存在。
 * 我们可以理解为生产者和消费者互相等待，等到对方之后然后再一起离开
 */
class NetCacheUtils(var localCacheUtils: LocalCacheUtils?, var memoryCacheUtils: MemoryCacheUtils?) {
    private val core_pool_size = Runtime.getRuntime().availableProcessors() + 1
    private val max_pool_size = core_pool_size * 2 + 1
    private val keep_alive_time = 5L
    private val time_unit = TimeUnit.SECONDS
    private val queue_size = 128

    private var executorService: ExecutorService? = null
    private var taskQueue: BlockingDeque<Runnable>? = null

    init {
        taskQueue = LinkedBlockingDeque<Runnable>(queue_size)
        executorService = ThreadPoolExecutor(core_pool_size, max_pool_size, keep_alive_time, time_unit, taskQueue)
    }

    fun getBitmapFromNet(url: String, callback: OnBitmapCallback) {
        executorService?.submit(object : FutureTask<Bitmap>(Callable<Bitmap> { loadBitmap(url) }) {

            override fun done() {
                try {
                    callback.onSuccess(get())
                } catch (e: Exception) {
                    callback.onError(e)
                }
            }
        })
    }

    fun loadBitmap(url: String): Bitmap? {
        var conn: HttpURLConnection? = null
        try {
            conn = URL(url).openConnection() as HttpURLConnection?
            conn?.connectTimeout = 5000;
            conn?.readTimeout = 5000;
            conn?.requestMethod = "GET";

            val responseCode: Int = conn?.responseCode ?: -1
            if (responseCode == 200) {
                val options = BitmapFactory.Options()
                //宽高压缩为原来的1/2
                options.inSampleSize = 2
                options.inPreferredConfig = Bitmap.Config.RGB_565
                return BitmapFactory.decodeStream(conn?.inputStream, null, options)
            }
        } catch (e: Exception) {
            Log.i("image", "e = ${e.toString()}")
        } finally {
            conn?.disconnect()
        }

        return null
    }

    interface OnBitmapCallback {

        fun onSuccess(bit: Bitmap)

        fun onError(e: Exception)
    }
}