package com.tanghong.one.ui.other

import android.view.View
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BridgeWebChromeClient : WebChromeClient() {

    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
    }

    override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
        return super.onJsAlert(view, url, message, result)
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        super.onShowCustomView(view, callback)
    }

    override fun onHideCustomView() {
        super.onHideCustomView()
    }
}