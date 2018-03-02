package com.tanghong.one.ui.search

import com.tanghong.commonlibrary.base.IBaseView
import com.tanghong.commonlibrary.base.IPresenter
import http.ApiException
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
interface SearchContract {

    interface View : IBaseView {

        fun setBanners(isRefresh: Boolean, type: String, result: Result<List<Banner>>)

        fun setHotAuthors(result: Result<List<User>>)

        fun setError(isRefresh: Boolean, e: ApiException)
    }

    interface Presenter : IPresenter<View> {

        fun loadBanners(isRefresh: Boolean, type: String, last_id: String)

        fun loadHotAuthors()
    }
}