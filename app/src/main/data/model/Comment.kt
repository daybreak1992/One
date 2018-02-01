package model

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/31
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class Comment(var id: String, var quote: String, var content: String, var praisenum: String,
              var device_token: String, var del_flag: String, var reviewed: String, var user_info_id: String,
              var input_date: String, var created_at: String, var updated_at: String, var user: User,
              var touser: User, var type: String) {

}