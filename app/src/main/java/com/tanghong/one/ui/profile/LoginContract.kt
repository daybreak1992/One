package com.tanghong.one.ui.profile

import com.tanghong.commonlibrary.base.IBaseView
import com.tanghong.commonlibrary.base.IPresenter
import http.ApiException
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
interface LoginContract {

    interface View : IBaseView {
        fun login(result: Result<Account>)

        fun setUser(result: Result<User>)

        fun setError(e: ApiException)
    }

    interface Presenter : IPresenter<View> {

        fun login(user_name: String, sex: String, reg_type: String, uid: String)

        fun loadUser(id: String, user_id: String, token: String)

        fun saveAccount(vararg account: Account)

        fun saveUsers(vararg user: User)
    }
}