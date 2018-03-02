package http

import com.tanghong.one.app.Constants
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

    override fun onNext(t: T) = when (t) {
        is Result<*> -> {
            if (t.res == 0) {
                dataCallback.onSuccess(t)
            } else {
                dataCallback.onError(ApiException(t.res, Constants.error_msg_data))
            }
        }
        else -> {
            dataCallback.onSuccess(t)
        }
    }

    override fun onError(t: Throwable?) =
            dataCallback.onError(ApiException(Constants.error_code_request, t?.message ?: Constants.error_msg_request))
}