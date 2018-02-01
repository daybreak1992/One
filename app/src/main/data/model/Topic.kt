package model

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class Topic(var id: String, var cover: String, var title: String, var category: String, var content_id: String,
            var is_stick: Boolean, var serial_list: List<String>, var link_url: String,
            var user_id: String, var user_name: String, var desc: String, var wb_name: String, var is_settled: String
            , var settled_type: String, var summary: String, var fans_total: String, var web_url: String) {

}