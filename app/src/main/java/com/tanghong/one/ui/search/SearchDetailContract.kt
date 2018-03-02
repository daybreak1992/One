package com.tanghong.one.ui.search

import com.tanghong.commonlibrary.base.IBaseView
import com.tanghong.commonlibrary.base.IPresenter
import http.ApiException
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
interface SearchDetailContract {

    interface View : IBaseView {

        fun setSearchWord(isRefresh: Boolean, result: Result<Search>)

        fun setError(isRefresh: Boolean, e: ApiException)
    }

    interface Presenter : IPresenter<View> {

        fun loadSearchWord(isRefresh: Boolean, type: String, word: String, page: Int)
    }
}