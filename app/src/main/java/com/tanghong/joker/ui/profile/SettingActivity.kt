package com.tanghong.joker.ui.profile

import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.commonlibrary.utils.AppUtils
import com.tanghong.joker.R
import com.tanghong.joker.app.App
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SettingActivity : BaseActivity<SettingPresenter>(), SettingContract.View, View.OnClickListener {

    override fun initPresenter(): SettingPresenter = SettingPresenter()

    override fun layoutId(): Int = R.layout.activity_setting

    override fun initView() {
        presenter.attachView(this)
        toolbar.setTitle(getString(R.string.title_setting))
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        rl_clear_cache.setOnClickListener(this)
        rl_exit_login.setOnClickListener(this)

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initData() {
        tv_app_version.text = AppUtils.getVerName()
        if (App.isLogin()) {
            rl_exit_login.visibility = View.VISIBLE
        } else {
            rl_exit_login.visibility = View.GONE
        }
        presenter.getCacheSize()
    }

    override fun start() {

    }

    override fun setCacheSize(size: String) {
        tv_cache_size.text = size
    }

    override fun setClearCache(success: Boolean) {
        closeProgress()
        if (success) {
            toast(R.string.prompt_clear_success)
            presenter.getCacheSize()
        } else {
            toast(R.string.prompt_clear_fail)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rl_clear_cache -> {
                MaterialDialog.Builder(this)
                        .title(R.string.title_prompt)
                        .content(R.string.content_clear_cache)
                        .positiveText(R.string.btn_confirm)
                        .negativeText(R.string.btn_cancel)
                        .onPositive { dialog, which ->
                            showProgress()
                            presenter.clearCache()
                        }
                        .show()
            }
            R.id.rl_exit_login -> {
                MaterialDialog.Builder(this)
                        .title(R.string.title_prompt)
                        .content(R.string.content_exit_app)
                        .positiveText(R.string.btn_confirm)
                        .negativeText(R.string.btn_cancel)
                        .onPositive { dialog, which ->
                            App.resetUser(null)
                            finish()
                        }
                        .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}