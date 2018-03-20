package com.tanghong.one.ui.other

import android.os.Environment
import android.os.Handler
import android.os.Message
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.commonlibrary.utils.FixDexUtils
import com.tanghong.one.R
import com.tanghong.one.repair.HotRepair
import com.tanghong.one.service.AsyncThread
import kotlinx.android.synthetic.main.activity_experiment.*
import kotlinx.android.synthetic.main.activity_user_info.*

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
        toolbar.title = getString(R.string.title_experiment)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener { finish() }
        btn1.setOnClickListener {
            val hotRepair = HotRepair()
            hotRepair.divide(this)
        }
        btn2.setOnClickListener {
            FixDexUtils.loadFixedDex(this, Environment.getExternalStorageDirectory())
        }
    }

    override fun initData() {

    }

    private fun initHandlerThread() {
        asyncThread = AsyncThread("asyncThread")
        childHandler = Handler(asyncThread?.looper, ChildCallback())
    }

    override fun start() {

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