package model

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
open class Content(var id: String, var category: String, var display_category: String, var item_id: String,
                   var title: String, var forward: String, var img_url: String, var like_count: String,
                   var post_date: String, var video_url: String, var audio_url: String, var audio_platform: String,
                   var start_video: String, var has_reading: String, var volume: String, var pic_info: String,
                   var words_info: String, var subtitle: String, var number: String, var serial_id: String,
                   var movie_story_id: String, var ad_id: String, var ad_type: String, var ad_pvurl: String,
                   var ad_linkurl: String, var ad_makettime: String, var ad_closetime: String, var ad_share_cnt: String,
                   var ad_pvurl_vendor: String, var content_id: String, var content_type: String, var content_bgcolor: String,
                   var share_url: String, var author: User, var share_info: Share, var serial_list: List<String>,
                   var default_audios: List<String>, var answerer: User, var music_name: String, var audio_author: String,
                   var audio_album: String, var cover: String, var bg_color: String, var tag_list: List<Tag>) {

    companion object {
        val illustration = 0
        val article = 1
        val serial = 2
        val questions_answers = 3
        val music = 4
        val movie = 5
        val radio = 8
        val topic = 11

        val essay_detail = "essay"
        val question_detail = "question"
        val serialcontent_detail = "serialcontent"
        val music_detail = "music"
        val movie_detail = "movie"
        val topic_detail = "topic"

        val category_illustration = "图文"
        val category_article = "阅读"
        val category_serial = "连载"
        val category_questions_answers = "问答"
        val category_music = "音乐"
        val category_movie = "影视"
        val category_radio = "电台"
    }
}