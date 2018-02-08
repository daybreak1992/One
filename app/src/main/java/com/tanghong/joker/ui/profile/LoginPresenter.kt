package com.tanghong.joker.ui.profile

import com.tanghong.commonlibrary.base.BasePresenter
import com.tanghong.commonlibrary.utils.RxUtils
import com.tanghong.joker.createRepository
import http.ApiException
import http.DataCallback
import http.DataSubscriber
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
class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun login(user_name: String, sex: String, reg_type: String, uid: String)
            = addSubscription(
            createRepository().login(user_name, sex, reg_type, uid)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(DataSubscriber<Result<Account>>(object : DataCallback<Result<Account>> {
                        override fun onSuccess(result: Result<Account>) {
                            rootView?.login(result)
                        }

                        override fun onError(e: ApiException) {
                            rootView?.setError(e)
                        }
                    }))
    )


    override fun loadUser(id: String, user_id: String, token: String)
            = addSubscription(
            createRepository().getUser(id, user_id, token)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(DataSubscriber<Result<User>>(object : DataCallback<Result<User>> {
                        override fun onSuccess(result: Result<User>) {
                            rootView?.setUser(result)
                        }

                        override fun onError(e: ApiException) {
                            rootView?.setError(e)
                        }
                    }))
    )
}