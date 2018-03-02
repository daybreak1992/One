package com.tanghong.one.ui.profile

import com.tanghong.commonlibrary.base.BasePresenter
import com.tanghong.commonlibrary.utils.RxUtils
import com.tanghong.one.createRepository
import http.ApiException
import http.DataCallback
import http.DataSubscriber
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
class UserInfoPresenter : BasePresenter<UserInfoContract.View>(), UserInfoContract.Presenter {

    override fun loadUser(id: String) = addSubscription(
            createRepository().getUser(id)
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