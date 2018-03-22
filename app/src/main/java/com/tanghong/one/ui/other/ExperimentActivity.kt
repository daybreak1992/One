package com.tanghong.one.ui.other

import android.os.Environment
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.ViewGroup
import com.qihoo360.replugin.RePlugin
import com.scwang.smartrefresh.layout.util.DensityUtil
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.commonlibrary.utils.FixDexUtils
import com.tanghong.commonlibrary.widget.SketchpadView
import com.tanghong.one.R
import com.tanghong.one.repair.HotRepair
import com.tanghong.one.service.AsyncThread
import kotlinx.android.synthetic.main.activity_experiment.*
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast

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
        tab1.setOnClickListener {
            try {
                val onePluginPath = Environment.getExternalStorageDirectory().absolutePath +
                        "/maimeng/apk/one_plugin.apk"
                Log.i("RePlugin", "path = $onePluginPath")
                RePlugin.install(onePluginPath)
            } catch (e: Exception) {
                toast("RePlugin install error = ${e.toString()}")
            } finally {
                toast("RePlugin install success")
            }
        }
        tab2.setOnClickListener {
            //            val intent = Intent()
//            intent.setClassName("oneplugin", "com.tanghong.oneplugin.MainActivity")
//            intent.component = ComponentName("oneplugin", "com.tanghong.oneplugin.MainActivity")
//            startActivity(intent)

            RePlugin.startActivity(this, RePlugin.createIntent("com.tanghong.oneplugin",
                    "com.tanghong.oneplugin.MainActivity"))
        }
        tab3.setOnClickListener {
            val sketchpadView: SketchpadView = SketchpadView(this)
            val params = ViewGroup.LayoutParams(DensityUtil.dp2px(300f), DensityUtil.dp2px(300f))
            sketchpadView.layoutParams = params
            cl_container.addView(sketchpadView)
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