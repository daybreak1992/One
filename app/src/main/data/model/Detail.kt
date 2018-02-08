package model

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */
data class Detail(var audio: String, var anchor: String, var category: String, var id: String,
             var title: String, var web_url: String, var author_list: List<User>, var tag_list: List<Tag>,
             var html_content: String, var platform: String, var music_id: String,
             var music_exception: String, var praisenum: String, var commentnum: String) {

}