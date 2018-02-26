package com.tanghong.joker.ui.other

import android.content.Context
import android.view.ViewGroup
import com.tanghong.joker.app.App
import com.tencent.smtt.sdk.CookieSyncManager
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/31
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object X5WebViewFactory {
    private var webView: WebView? = null

    fun createWebView(context: Context): WebView {
        if (webView == null) {
            webView = WebView(context)
            initWebSettings(context)
        }
        return webView!!
    }

    private fun initWebSettings(context: Context) {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        webView?.setLayoutParams(layoutParams)
        webView?.setWebViewClient(X5WebViewClient())
        webView?.setWebChromeClient(X5WebChromeClient())
        webView?.setDownloadListener(X5DownloadListener())
        val webSettings = webView!!.getSettings()
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(true);
//        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setAppCacheMaxSize(10 * 1024 * 1024);
        webSettings.setAppCachePath(App.appContext.getDir("one_cache", 0).getPath());
        webSettings.setDatabasePath(App.appContext.getDir("one_datbase", 0).getPath());
        webSettings.setGeolocationDatabasePath(App.appContext.getDir("GeolocationDatabase", 0).getPath());
//        webSettings.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
//        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension settings 的设计
        CookieSyncManager.createInstance(context);
        CookieSyncManager.getInstance().sync();
    }

    fun loadDataWithBaseURL(baseUrl: String, data: String, mimeType: String,
                            encoding: String, historyUrl: String) {
        webView?.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl)
    }

    fun loadUrl(url: String) {
        webView?.loadUrl(url)
    }

    fun loadUrl(url: String, headers: Map<String, String>) {
        webView?.loadUrl(url, headers)
    }

    fun destroyWebView() {
        if (webView != null) {
            webView?.destroy()
            webView = null
        }
    }
}