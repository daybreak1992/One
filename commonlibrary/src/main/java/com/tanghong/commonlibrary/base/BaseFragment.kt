package com.tanghong.commonlibrary.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
abstract class BaseFragment<out T> : Fragment(), IBaseView {

    private var progressDialog: MaterialDialog? = null

    val presenter: T by lazy {
        initPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(layoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            progressDialog = MaterialDialog.Builder(activity!!)
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