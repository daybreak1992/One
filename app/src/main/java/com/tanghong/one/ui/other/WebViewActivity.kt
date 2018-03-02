package com.tanghong.one.ui.other

import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import com.github.lzyzsd.jsbridge.BridgeWebViewClient
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.one.R
import com.tanghong.one.app.App
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class WebViewActivity : BaseActivity<WebViewPresenter>(), WebViewContract.View {

    override fun initPresenter(): WebViewPresenter = WebViewPresenter()

    override fun layoutId(): Int = R.layout.activity_webview

    override fun initView() {
        presenter.attachView(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        bwv.setDefaultHandler { data, function ->
            Log.i("webView", "defaultHandler = $data")
        }
        bwv.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            allowFileAccess = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            setAppCacheEnabled(true)
            setAppCacheMaxSize(10 * 1024 * 1024)
            setAppCachePath(App.appContext.cacheDir.absolutePath)
        }
        bwv.webViewClient = object : BridgeWebViewClient(bwv) {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
        bwv.webChromeClient = object : WebChromeClient() {

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                toolbar.title = title
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)

            }
        }
        bwv.registerHandler("prepareAudios", { data, function ->
            Log.i("webView", "prepareAudios = $data")
        })
        bwv.registerHandler("playAudio", { data, function ->
            Log.i("webView", "playAudio = $data")
        })
        bwv.registerHandler("playRadio", { data, function ->
            Log.i("webView", "playRadio = $data")
        })
        bwv.registerHandler("playMusic", { data, function ->
            Log.i("webView", "playMusic = $data")
        })
        bwv.registerHandler("followAuthor", { data, function ->
            Log.i("webView", "followAuthor = $data")
        })
    }

    override fun initData() {
        bwv.loadDataWithBaseURL("", intent.getStringExtra("web_data"), "text/html",
                "utf-8", "")
    }

    override fun start() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}