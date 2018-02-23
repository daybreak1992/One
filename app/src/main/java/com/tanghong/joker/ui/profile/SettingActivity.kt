package com.tanghong.joker.ui.profile

import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.joker.R

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SettingActivity : BaseActivity<SettingPresenter>(), SettingContract.View {

    override fun initPresenter(): SettingPresenter = SettingPresenter()

    override fun layoutId(): Int = R.layout.activity_setting

    override fun initView() {
        presenter.attachView(this)
    }

    override fun initData() {

    }

    override fun start() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}