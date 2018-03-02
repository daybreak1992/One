package com.tanghong.one.ui.main

import com.tanghong.commonlibrary.base.BasePresenter
import com.tanghong.commonlibrary.utils.RxUtils
import com.tanghong.one.createRepository
import http.ApiException
import http.DataCallback
import http.DataSubscriber
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
class DetailPresenter : BasePresenter<DetailContract.View>(), DetailContract.Presenter {

    override fun loadDetail(category: String, id: String, source_id: String)
            = addSubscription(
            createRepository().getDetail(category, id, source_id)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(DataSubscriber<Result<Detail>>(object : DataCallback<Result<Detail>> {
                        override fun onSuccess(result: Result<Detail>) {
                            rootView?.setDetailData(result)
                        }

                        override fun onError(e: ApiException) {
                            rootView?.setError(true, e)
                        }
                    }))
    )

    override fun loadDetailComments(isRefresh: Boolean, category: String, id: String, lastCommentId: String)
            = addSubscription(
            createRepository().getDetailComment(category, id, lastCommentId)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(DataSubscriber<Result<CommentRoot>>(object : DataCallback<Result<CommentRoot>> {
                        override fun onSuccess(result: Result<CommentRoot>) {
                            rootView?.setDetailComments(isRefresh, result)
                        }

                        override fun onError(e: ApiException) {
                            rootView?.setError(isRefresh, e)
                        }
                    }))
    )
}