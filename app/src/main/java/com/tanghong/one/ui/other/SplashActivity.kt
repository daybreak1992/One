package com.tanghong.one.ui.other

import android.Manifest
import android.os.Handler
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.one.R
import com.tanghong.one.openPage
import com.tanghong.one.ui.main.MainActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class SplashActivity : BaseActivity<SplashPresenter>(), SplashContract.View {

    val handler = Handler()

    companion object {
        private val AUTO_HIDE_DELAY_MILLIS: Long = 2000
    }

    override fun initPresenter(): SplashPresenter = SplashPresenter()

    override fun layoutId(): Int = R.layout.activity_splash

    override fun initView() {
        presenter.attachView(this)
        val resIds = arrayOf(R.drawable.opening_sunday, R.drawable.opening_monday,
                R.drawable.opening_tuesday, R.drawable.opening_wednesday, R.drawable.opening_thursday,
                R.drawable.opening_friday, R.drawable.opening_saturday)
        iv_day.setImageResource(resIds[getWeekIndex()])
        tv_date.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
    }

    override fun initData() {

    }

    override fun start() {
        RxPermissions(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                .subscribe({ grant: Boolean ->
                    if (!grant) {
                        toast(R.string.prompt_permission_grant)
                    }
                    handler.postDelayed({
                        openPage(MainActivity::class.java)
                        finish()
                    }, AUTO_HIDE_DELAY_MILLIS)
                }, {
                    toast(R.string.prompt_permission_error)
                })
    }

    private fun getWeekIndex(): Int {
        val cal = Calendar.getInstance()
        cal.time = Date()
        var w = cal.get(Calendar.DAY_OF_WEEK) - 1
        if (w < 0) {
            w = 0
        }
        return w
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
