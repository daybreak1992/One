package com.tanghong.one.ui.other

import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.one.R
import com.tanghong.one.service.AsyncThread

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class ExperimentActivity : BaseActivity<ExperimentPresenter>(), ExperimentContract.View {
    private var asyncThread: AsyncThread? = null
    private var childHandler: Handler? = null

    override fun initPresenter(): ExperimentPresenter = ExperimentPresenter()

    override fun layoutId(): Int = R.layout.activity_experiment

    override fun initView() {
        presenter.attachView(this)
    }

    override fun initData() {
        initHandlerThread()
    }

    private fun initHandlerThread() {
        asyncThread = AsyncThread("asyncThread")
        childHandler = Handler(asyncThread?.looper, ChildCallback())
    }

    override fun start() {
        val task = object :AsyncTask<String, Int, Boolean>() {

            override fun doInBackground(vararg params: String?): Boolean {
                return false
            }
        }
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "")

        startForegroundService()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    class ChildCallback : Handler.Callback {

        override fun handleMessage(msg: Message?): Boolean {

            return false
        }
    }
}