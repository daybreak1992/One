package com.tanghong.one.ui.main

import com.tanghong.commonlibrary.base.IBaseView
import com.tanghong.commonlibrary.base.IPresenter
import http.ApiException
import model.User

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface MainContract {

    interface View : IBaseView {
        fun setUser(user: List<User>)

        fun setError(e: ApiException)
    }

    interface Presenter : IPresenter<View> {

        fun getUser()
    }
}