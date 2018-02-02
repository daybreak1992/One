package com.tanghong.joker.ui.search

import com.tanghong.commonlibrary.base.BasePresenter
import com.tanghong.commonlibrary.utils.RxUtils
import com.tanghong.joker.createRepository
import http.ApiException
import http.DataCallback
import http.DataSubscriber
import model.Banner
import model.Result
import model.User

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SearchPresenter : BasePresenter<SearchContract.View>(), SearchContract.Presenter {

    override fun loadBanners(isRefresh: Boolean, type: String, last_id: String)
            = addSubscription(
            createRepository().getBanners(type, last_id)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(DataSubscriber<Result<List<Banner>>>(object : DataCallback<Result<List<Banner>>> {
                        override fun onSuccess(result: Result<List<Banner>>) {
                            rootView?.setBanners(isRefresh, type, result)
                        }

                        override fun onError(e: ApiException) {
                            rootView?.setError(isRefresh, e)
                        }
                    }))
    )

    override fun loadHotAuthors()
            = addSubscription(
            createRepository().getHotAuthors()
                    .compose(RxUtils.composeIo())
                    .subscribeWith(DataSubscriber<Result<List<User>>>(object : DataCallback<Result<List<User>>> {
                        override fun onSuccess(result: Result<List<User>>) {
                            rootView?.setHotAuthors(result)
                        }

                        override fun onError(e: ApiException) {
                            rootView?.setError(true, e)
                        }
                    }))
    )
}