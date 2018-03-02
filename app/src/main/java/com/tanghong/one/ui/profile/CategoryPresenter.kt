package com.tanghong.one.ui.profile

import com.tanghong.commonlibrary.base.BasePresenter
import com.tanghong.commonlibrary.utils.RxUtils
import com.tanghong.one.createRepository
import http.ApiException
import http.DataCallback
import http.DataSubscriber
import model.Category

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    override fun loadCategory(id: String)
            = addSubscription(
            createRepository().getCategory(id)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(DataSubscriber<Category>(object : DataCallback<Category> {
                        override fun onSuccess(result: Category) {
                            rootView?.setCategory(result)
                        }

                        override fun onError(e: ApiException) {
                            rootView?.setError(e)
                        }
                    }))
    )
}