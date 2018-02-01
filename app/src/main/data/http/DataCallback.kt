package http

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface DataCallback<T> {

    fun onSuccess(result: T)

    fun onError(e: ApiException)
}