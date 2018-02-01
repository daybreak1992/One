package com.tanghong.commonlibrary.utils

import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class RxUtils private constructor() {

    init {
        throw Throwable("Not allowed to initialize.")
    }

    companion object {

        fun <T> composeIo(): FlowableTransformer<T, T> = FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }

        fun <T> composeNewThread(): FlowableTransformer<T, T> = FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}