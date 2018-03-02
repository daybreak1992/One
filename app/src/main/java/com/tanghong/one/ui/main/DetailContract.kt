package com.tanghong.one.ui.main

import com.tanghong.commonlibrary.base.IBaseView
import com.tanghong.commonlibrary.base.IPresenter
import http.ApiException
import model.CommentRoot
import model.Detail
import model.Result

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface DetailContract {

    interface View : IBaseView {

        fun setDetailData(result: Result<Detail>)

        fun setDetailComments(isRefresh: Boolean, result: Result<CommentRoot>)

        fun setError(isRefresh: Boolean, e: ApiException)
    }

    interface Presenter : IPresenter<View> {

        fun loadDetail(category: String, id: String, source_id: String)

        fun loadDetailComments(isRefresh: Boolean, category: String, id: String, lastCommentId: String = "0")
    }
}