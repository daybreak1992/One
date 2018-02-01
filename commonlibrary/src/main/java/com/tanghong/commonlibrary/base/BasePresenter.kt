package com.tanghong.commonlibrary.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
open class BasePresenter<T : IBaseView> : IPresenter<T> {

    var rootView: T? = null
        private set

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun attachView(rootView: T) {
        this.rootView = rootView
    }

    override fun detachView() {
        rootView = null
        if (!compositeDisposable.isDisposed)
            compositeDisposable.clear()
    }

    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}