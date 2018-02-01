package http

import com.tanghong.joker.app.Constants
import io.reactivex.subscribers.ResourceSubscriber
import model.Result

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class DataSubscriber<T>(val dataCallback: DataCallback<T>) : ResourceSubscriber<T>() {

    override fun onComplete() {

    }

    override fun onNext(t: T) {
        val result: Result<*> = t as Result<*>;
        if (result.res == 0) {
            dataCallback.onSuccess(t)
        } else {
            dataCallback.onError(ApiException(result.res, Constants.error_msg_data))
        }
    }

    override fun onError(t: Throwable?) =
            dataCallback.onError(ApiException(Constants.error_code_request, t?.message ?: Constants.error_msg_request))
}