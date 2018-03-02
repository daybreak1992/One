package com.tanghong.one.ui.profile

import com.tanghong.commonlibrary.base.IBaseView
import com.tanghong.commonlibrary.base.IPresenter
import http.ApiException
import model.Result
import model.User

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface UserInfoContract {

    interface View : IBaseView {
        fun setUser(result: Result<User>)

        fun setError(e: ApiException)
    }

    interface Presenter : IPresenter<View> {

        fun loadUser(id: String)
    }
}