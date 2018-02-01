package com.tanghong.commonlibrary.base

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface IPresenter<in V : IBaseView> {

    fun attachView(rootView: V)

    fun detachView()
}