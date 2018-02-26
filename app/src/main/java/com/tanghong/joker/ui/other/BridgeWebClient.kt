package com.tanghong.joker.ui.other

import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.github.lzyzsd.jsbridge.BridgeWebView
import com.github.lzyzsd.jsbridge.BridgeWebViewClient

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BridgeWebClient(bridgeWebView: BridgeWebView) : BridgeWebViewClient(bridgeWebView) {

    /**
     * 1、 默认返回：return super.shouldOverrideUrlLoading(view, url); 这个返回的方法会调用父类方法，
     * 也就是跳转至手机浏览器，平时写webview一般都在方法里面写 webView.loadUrl(url); 然后把这个返回值改成下面的false。
     * 2、返回: return true; webview处理url是根据程序来执行的。
     * 3、返回: return false; webview处理url是在webview内部执行。
     */
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return false
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return false
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
//        super.onReceivedSslError(view, handler, error)
        //设置 WebView 接受所有网站的证书
        handler?.proceed()
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        super.onReceivedError(view, request, error)
    }
}