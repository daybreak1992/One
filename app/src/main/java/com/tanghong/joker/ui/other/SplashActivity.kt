package com.tanghong.joker.ui.other

import android.os.Handler
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.joker.R
import com.tanghong.joker.openPage
import com.tanghong.joker.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
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
        handler.postDelayed(object : Runnable {
            override fun run() {
                openPage(MainActivity::class.java)
                finish()
            }

        }, AUTO_HIDE_DELAY_MILLIS)
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
