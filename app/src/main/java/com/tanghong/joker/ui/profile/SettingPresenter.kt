package com.tanghong.joker.ui.profile

import com.tanghong.commonlibrary.base.BasePresenter
import com.tanghong.commonlibrary.utils.AppUtils
import com.tanghong.commonlibrary.utils.RxUtils
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.subscribers.ResourceSubscriber

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SettingPresenter : BasePresenter<SettingContract.View>(), SettingContract.Presenter {

    override fun getCacheSize() = addSubscription(
            Flowable
                    .create(FlowableOnSubscribe<String> { emitter ->
                        try {
                            emitter.onNext(AppUtils.getCacheSize())
                            emitter.onComplete()
                        } catch (e: Exception) {
                            emitter.onError(e)
                        }
                    }, BackpressureStrategy.BUFFER)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(object : ResourceSubscriber<String>() {
                        override fun onComplete() {

                        }

                        override fun onNext(t: String?) {
                            rootView?.setCacheSize(t ?: "0KB")
                        }

                        override fun onError(t: Throwable?) {
                            rootView?.setCacheSize(t?.message ?: "0KB")
                        }
                    })
    )

    override fun clearCache() = addSubscription(
            Flowable
                    .create(FlowableOnSubscribe<Boolean> { emitter ->
                        try {
                            emitter.onNext(AppUtils.clearCache())
                            emitter.onComplete()
                        } catch (e: Exception) {
                            emitter.onError(e)
                        }
                    }, BackpressureStrategy.BUFFER)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(object : ResourceSubscriber<Boolean>() {
                        override fun onComplete() {

                        }

                        override fun onNext(t: Boolean?) {
                            rootView?.setClearCache(t ?: false)
                        }

                        override fun onError(t: Throwable?) {
                            rootView?.setClearCache(false)
                        }
                    })
    )
}