package com.tanghong.one.ui.search

import com.tanghong.commonlibrary.base.BasePresenter
import com.tanghong.commonlibrary.utils.RxUtils
import com.tanghong.one.createRepository
import http.ApiException
import http.DataCallback
import http.DataSubscriber
import model.Result
import model.Search

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SearchDetailPresenter : BasePresenter<SearchDetailContract.View>(), SearchDetailContract.Presenter {

    override fun loadSearchWord(isRefresh: Boolean, type: String, word: String, page: Int) = addSubscription(
            createRepository().getSearchWord(type, word, page)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(DataSubscriber<Result<Search>>(object : DataCallback<Result<Search>> {
                        override fun onSuccess(result: Result<Search>) {
                            rootView?.setSearchWord(isRefresh, result)
                        }

                        override fun onError(e: ApiException) {
                            rootView?.setError(isRefresh, e)
                        }
                    }))
    )
}