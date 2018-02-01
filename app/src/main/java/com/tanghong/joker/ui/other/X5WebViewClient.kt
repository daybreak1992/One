package com.tanghong.joker.ui.other

import android.graphics.Bitmap
import com.tencent.smtt.export.external.interfaces.SslError
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.export.external.interfaces.WebResourceError
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/31
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class X5WebViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(webView: WebView?, url: String?): Boolean {
        return false
    }

    override fun onPageStarted(webView: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(webView, url, favicon)
    }

    override fun onPageFinished(webView: WebView?, url: String?) {
        super.onPageFinished(webView, url)
    }

    override fun onReceivedSslError(view: WebView?, hanlder: SslErrorHandler?, error: SslError?) {
//        super.onReceivedSslError(view, hanlder, error)
        hanlder?.proceed()
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        super.onReceivedError(view, request, error)
    }
}