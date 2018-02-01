package com.tanghong.joker.ui.main

import com.tanghong.commonlibrary.base.IBaseView
import com.tanghong.commonlibrary.base.IPresenter
import http.ApiException
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
interface HomeContract {

    interface View : IBaseView {

        fun setOne(result: Result<One>)

        fun setError(e: ApiException)
    }

    interface Presenter : IPresenter<View> {

        fun loadOne(date:String, city:String)
    }
}