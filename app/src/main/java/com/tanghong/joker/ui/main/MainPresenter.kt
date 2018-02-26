package com.tanghong.joker.ui.main

import com.tanghong.commonlibrary.base.BasePresenter
import com.tanghong.commonlibrary.utils.RxUtils
import com.tanghong.joker.app.Constants
import com.tanghong.joker.createRepository
import http.ApiException
import io.reactivex.subscribers.ResourceSubscriber
import model.User

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun getUser() = addSubscription(
            createRepository().getUser()
                    .compose(RxUtils.composeIo())
                    .subscribeWith(object : ResourceSubscriber<List<User>>() {
                        override fun onComplete() {

                        }

                        override fun onNext(t: List<User>?) {
                            if (t != null && !t.isEmpty()) {
                                rootView?.setUser(t)
                            } else {
                                rootView?.setError(ApiException(Constants.error_code_empty, Constants.error_msg_empty))
                            }
                        }

                        override fun onError(t: Throwable?) {
                            rootView?.setError(ApiException(Constants.error_code_db, t?.message
                                    ?: Constants.error_msg_db_data))
                        }
                    })
    )
}