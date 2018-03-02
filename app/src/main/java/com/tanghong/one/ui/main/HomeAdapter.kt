package com.tanghong.one.ui.main

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.tanghong.commonlibrary.base.adapter.BaseAdapter
import com.tanghong.commonlibrary.base.adapter.BaseViewHolder
import com.tanghong.commonlibrary.base.adapter.MultiType
import com.tanghong.commonlibrary.utils.ScreenUtils
import com.tanghong.commonlibrary.utils.TimeDateUtils
import com.tanghong.one.R
import com.tanghong.one.glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import model.Content

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class HomeAdapter(datas: ArrayList<Content>) : BaseAdapter<Content>(datas,
        object : MultiType<Content> {
            override fun layoutId(item: Content, pos: Int): Int = when (item.category.toInt()) {
                Content.illustration -> R.layout.layout_home_item_illustration
                Content.article -> R.layout.layout_home_item_default
                Content.serial -> R.layout.layout_home_item_default
                Content.questions_answers -> R.layout.layout_home_item_default
                Content.movie -> R.layout.layout_home_item_movie
                Content.music -> R.layout.layout_home_item_music
                Content.radio -> R.layout.layout_home_item_radio
                else -> R.layout.layout_home_item_default
            }
        }) {

    override fun bindData(holder: BaseViewHolder, data: Content, position: Int) {
        with(data) {
            when (category.toInt()) {
                Content.illustration -> {
                    holder.setText(R.id.tv_like_count, like_count)
                    holder.setText(R.id.tv_title_author, "$title/$pic_info")
                    holder.setText(R.id.tv_forward, forward)
                    holder.setText(R.id.tv_words_info, words_info)
                    holder.getView<ImageView>(R.id.iv_cover).glide(img_url)
                }
                Content.article -> {
                    if (tag_list.isEmpty()) {
                        holder.setText(R.id.tv_tag_title, "— ${context.getString(R.string.title_read)} —")
                    } else {
                        holder.setText(R.id.tv_tag_title, "— ${tag_list[0].title} —")
                    }
                    holder.setText(R.id.tv_title, title)
                    holder.setText(R.id.tv_author, context.getString(R.id.title_article) + "/${author.user_name}")
                    holder.setText(R.id.tv_forward, forward)
                    holder.setText(R.id.tv_like_count, like_count)
                    holder.setText(R.id.tv_post_date, TimeDateUtils.formatTime(post_date))
                    holder.getView<ImageView>(R.id.iv_cover).glide(img_url)
                }
                Content.serial -> {
                    holder.setText(R.id.tv_tag_title, "— ${context.getString(R.string.title_serial)} —")
                    holder.setText(R.id.tv_title, title)
                    holder.setText(R.id.tv_author, context.getString(R.id.title_article) + "/${author.user_name}")
                    holder.setText(R.id.tv_forward, forward)
                    holder.setText(R.id.tv_like_count, like_count)
                    holder.setText(R.id.tv_post_date, TimeDateUtils.formatTime(post_date))
                    holder.getView<ImageView>(R.id.iv_cover).glide(img_url)
                }
                Content.questions_answers -> {
                    holder.setText(R.id.tv_tag_title, "— ${context.getString(R.string.title_questions_answers)} —")
                    holder.setText(R.id.tv_title, title)
                    holder.setText(R.id.tv_author, "${answerer.user_name}${context.getString(R.string.title_answer)}")
                    holder.setText(R.id.tv_forward, forward)
                    holder.setText(R.id.tv_like_count, like_count)
                    holder.setText(R.id.tv_post_date, TimeDateUtils.formatTime(post_date))
                    holder.getView<ImageView>(R.id.iv_cover).glide(img_url)
                }
                Content.movie -> {
                    holder.setText(R.id.tv_tag_title, "— ${context.getString(R.string.title_movie)} —")
                    holder.setText(R.id.tv_title, title)
                    holder.setText(R.id.tv_author, context.getString(R.id.title_article) + "/${author.user_name}")
                    holder.setText(R.id.tv_forward, forward)
                    holder.setText(R.id.tv_subtitle, "— —《${subtitle}》")
                    holder.setText(R.id.tv_like_count, like_count)
                    holder.setText(R.id.tv_post_date, TimeDateUtils.formatTime(post_date))
                    holder.getView<ImageView>(R.id.iv_cover).glide(img_url)
                }
                Content.music -> {
                    holder.setText(R.id.tv_tag_title, "— ${context.getString(R.string.title_music)} —")
                    holder.setText(R.id.tv_title, title)
                    holder.setText(R.id.tv_author, context.getString(R.id.title_article) + "/${author.user_name}")
                    holder.setText(R.id.tv_music_info, "$music_name · $audio_author | $audio_album")
                    holder.setText(R.id.tv_forward, forward)
                    holder.setText(R.id.tv_like_count, like_count)
                    holder.setText(R.id.tv_post_date, TimeDateUtils.formatTime(post_date))
                    holder.getView<ImageView>(R.id.iv_cover).glide(img_url,
                            RequestOptions.bitmapTransform(RoundedCornersTransformation(ScreenUtils.dip2px(90f), 0)))
                }
                Content.radio -> {
                    holder.setText(R.id.tv_title, title)
                    holder.setText(R.id.tv_like_count, like_count)
                    holder.getView<ImageView>(R.id.iv_cover).glide(img_url)
                }
                else -> {

                }
            }
        }
    }
}