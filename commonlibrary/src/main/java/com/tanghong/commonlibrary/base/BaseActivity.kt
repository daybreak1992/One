package com.tanghong.commonlibrary.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.tanghong.commonlibrary.R

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
abstract class BaseActivity<out T> : AppCompatActivity(), IBaseView {

    private var progressDialog: MaterialDialog? = null

    val presenter: T by lazy {
        initPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initView()
        initData()
        start()
    }

    abstract fun initPresenter(): T

    abstract fun layoutId(): Int

    abstract fun initView()

    abstract fun initData()

    abstract fun start()

    fun showProgress() =
            showProgress(getString(R.string.title_prompt), getString(R.string.content_loading))

    fun showProgress(title: String, content: String) {
        if (progressDialog == null) {
            progressDialog = MaterialDialog.Builder(this)
                    .title(title)
                    .content(content)
                    .progress(true, 0)
                    .build()
        }
        progressDialog?.show()
    }

    fun closeProgress() {
        progressDialog?.dismiss()
    }
}