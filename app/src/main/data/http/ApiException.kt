package http

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class ApiException(res: Int, msg: String) : Throwable(msg) {

    var res: Int? = res

}