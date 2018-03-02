package com.tanghong.one.ui.other

import android.content.Context
import android.os.Build
import android.webkit.WebSettings
import com.github.lzyzsd.jsbridge.BridgeWebView
import com.github.lzyzsd.jsbridge.DefaultHandler
import com.tanghong.one.app.App

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object BridgeWebViewFactory {

    /**
     * if (webView.getContentHeight() * webView.getScale() == (webView.getHeight() + webView.getScrollY())) {
     * //已经处于底端
     * }
     * if(webView.getScrollY() == 0){
     * //处于顶端
     * }
     */
    private var bridgeWebView: BridgeWebView? = null

    fun createWebView(context: Context): BridgeWebView {
        if (bridgeWebView == null) {
            bridgeWebView = BridgeWebView(context)
            initWebView()
        }
        return bridgeWebView!!
    }

    fun getBridgeWebView(): BridgeWebView? = bridgeWebView

    private fun initWebView() {
        bridgeWebView?.webViewClient = BridgeWebClient(bridgeWebView!!)
        bridgeWebView?.webChromeClient = BridgeWebChromeClient()
        bridgeWebView?.setDefaultHandler(DefaultHandler())
        bridgeWebView?.settings?.apply {
            //默认是false 设置true允许和js交互
            javaScriptEnabled = true
            //支持内容重新布局
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            //多窗口
            setSupportMultipleWindows(true)
            //开启 DOM storage API 功能 较大存储空间，使用简单
            domStorageEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            allowFileAccess = true
            //WebSettings.LOAD_DEFAULT 如果本地缓存可用且没有过期则使用本地缓存，否加载网络数据 默认值
            //WebSettings.LOAD_CACHE_ELSE_NETWORK 优先加载本地缓存数据，无论缓存是否过期
            //ebSettings.LOAD_NO_CACHE  只加载网络数据，不加载本地缓存
            //ebSettings.LOAD_CACHE_ONLY 只加载缓存数据，不加载网络数据
            //Tips:有网络可以使用LOAD_DEFAULT 没有网时用LOAD_CACHE_ELSE_NETWORK
            cacheMode = WebSettings.LOAD_NO_CACHE
            //设置数据库缓存路径 存储管理复杂数据 方便对数据进行增加、删除、修改、查询 不推荐使用
            databaseEnabled = true
            databasePath = App.appContext.getDir("one_datbase", Context.MODE_PRIVATE).getPath()
            //开启 Application Caches 功能 方便构建离线APP 不推荐使用
            setAppCacheEnabled(true)
            setAppCacheMaxSize(10 * 1024 * 1024)
            setAppCachePath(App.appContext.getDir("one_cache", Context.MODE_PRIVATE).getPath())
            defaultTextEncodingName = "utf-8"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
        }
    }

    fun loadDataWithBaseURL(baseUrl: String, data: String, mimeType: String,
                            encoding: String, historyUrl: String) {
        bridgeWebView?.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl)
    }

    fun loadUrl(url: String) {
        bridgeWebView?.loadUrl(url)
    }

    fun loadUrl(url: String, headers: Map<String, String>) {
        bridgeWebView?.loadUrl(url, headers)
    }

    fun destroyWebView() {
        if (bridgeWebView != null) {
            bridgeWebView?.destroy()
            bridgeWebView = null
        }
    }
}