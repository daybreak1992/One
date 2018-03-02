package com.tanghong.one.ui.main

import com.tanghong.commonlibrary.base.BasePresenter
import com.tanghong.commonlibrary.utils.RxUtils
import com.tanghong.one.createRepository
import http.ApiException
import http.DataCallback
import http.DataSubscriber
import model.One
import model.Result

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    override fun loadOne(date: String, city: String)
            = addSubscription(
            createRepository().getHome(date, city)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(DataSubscriber<Result<One>>(object : DataCallback<Result<One>> {

                        override fun onSuccess(result: Result<One>) {
                            rootView?.setOne(result)
                        }

                        override fun onError(e: ApiException) {
                            rootView?.setError(e)
                        }
                    }))
    )
}