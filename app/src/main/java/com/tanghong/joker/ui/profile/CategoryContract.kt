package com.tanghong.joker.ui.profile

import com.tanghong.commonlibrary.base.IBaseView
import com.tanghong.commonlibrary.base.IPresenter
import http.ApiException
import model.Category

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface CategoryContract {

    interface View : IBaseView {

        fun setCategory(result: Category)

        fun setError(e: ApiException)
    }

    interface Presenter : IPresenter<View> {

        fun loadCategory(id: String)
    }
}