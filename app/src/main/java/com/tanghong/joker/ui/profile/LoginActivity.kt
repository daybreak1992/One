package com.tanghong.joker.ui.profile

import android.util.Log
import android.view.View
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.commonlibrary.utils.JsonUtils
import com.tanghong.commonlibrary.utils.RxUtils
import com.tanghong.joker.R
import com.tanghong.joker.app.Constants
import db.helper.DbHelper
import http.ApiException
import io.reactivex.subscribers.ResourceSubscriber
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import model.Account
import model.Result
import model.User

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.View, View.OnClickListener {

    override fun initPresenter(): LoginPresenter = LoginPresenter()

    override fun layoutId(): Int = R.layout.activity_login

    override fun initView() {
        presenter.attachView(this)
        toolBar.setTitle(R.string.title_login)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_login.setOnClickListener(this)
        toolBar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
                presenter.login("***+****+0761", "0", "3", Constants.uid)
            }
        }
    }

    override fun initData() {
        DbHelper.getAccountDao().queryAll()
                .compose(RxUtils.composeIo())
                .subscribeWith(object : ResourceSubscriber<List<Account>>() {
                    override fun onNext(t: List<Account>?) {
                        Log.i("login", "onNext111 = ${JsonUtils.serializeToJson(t!!)}")
                    }

                    override fun onComplete() {

                    }

                    override fun onError(t: Throwable?) {
                        Log.i("login", "onError111 = ${t?.message}")
                    }
                })
    }

    override fun start() {

    }

    override fun login(result: Result<Account>) {
        Log.i("login", "result = ${JsonUtils.serializeToJson(result)}")
        DbHelper.getAccountDao().update(result.data)
    }

    override fun setUser(result: Result<User>) {

    }

    override fun setError(e: ApiException) {
        Log.i("login", "e = ${e.message}")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}