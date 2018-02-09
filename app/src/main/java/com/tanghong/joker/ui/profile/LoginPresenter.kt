package com.tanghong.joker.ui.profile

import com.tanghong.commonlibrary.base.BasePresenter
import com.tanghong.commonlibrary.utils.RxUtils
import com.tanghong.joker.createRepository
import db.helper.DbHelper
import http.ApiException
import http.DataCallback
import http.DataSubscriber
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.subscribers.ResourceSubscriber
import model.Account
import model.Result
import model.User

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun login(user_name: String, sex: String, reg_type: String, uid: String) = addSubscription(
            createRepository().login(user_name, sex, reg_type, uid)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(DataSubscriber<Result<Account>>(object : DataCallback<Result<Account>> {
                        override fun onSuccess(result: Result<Account>) {
                            rootView?.login(result)
                            saveAccount(result.data)
                        }

                        override fun onError(e: ApiException) {
                            rootView?.setError(e)
                        }
                    }))
    )

    override fun loadUser(id: String, user_id: String, token: String) = addSubscription(
            createRepository().getUser(id, user_id, token)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(DataSubscriber<Result<User>>(object : DataCallback<Result<User>> {
                        override fun onSuccess(result: Result<User>) {
                            rootView?.setUser(result)
                            val copyUser = result.data.copy(user_id = user_id)
                            saveUsers(copyUser)
                        }

                        override fun onError(e: ApiException) {
                            rootView?.setError(e)
                        }
                    }))
    )

    override fun saveAccount(vararg account: Account) = addSubscription(
            Flowable.create(FlowableOnSubscribe<List<Long>> { emitter ->
                try {
                    emitter.onNext(DbHelper.getAccountDao().insert(*account))
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }, BackpressureStrategy.BUFFER)
                    .compose(RxUtils.composeIo())
                    .subscribeWith(object : ResourceSubscriber<List<Long>>() {
                        override fun onComplete() {

                        }

                        override fun onNext(t: List<Long>?) {

                        }

                        override fun onError(t: Throwable?) {

                        }
                    })
    )

    override fun saveUsers(vararg user: User) =
            addSubscription(
                    Flowable.create(FlowableOnSubscribe<List<Long>> { emitter ->
                        try {
                            emitter.onNext(DbHelper.getUserDao().insert(*user))
                            emitter.onComplete()
                        } catch (e: Exception) {
                            emitter.onError(e)
                        }
                    }, BackpressureStrategy.BUFFER)
                            .compose(RxUtils.composeIo())
                            .subscribeWith(object : ResourceSubscriber<List<Long>>() {
                                override fun onComplete() {

                                }

                                override fun onNext(t: List<Long>?) {

                                }

                                override fun onError(t: Throwable?) {

                                }
                            })
            )
}