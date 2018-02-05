package com.tanghong.joker.ui.search

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import com.tanghong.commonlibrary.base.adapter.BaseAdapter
import com.tanghong.commonlibrary.base.adapter.BaseViewHolder
import com.tanghong.commonlibrary.base.adapter.MultiType
import com.tanghong.joker.R
import com.tanghong.joker.glide
import model.Banner
import model.SearchHeader
import model.User
import org.jetbrains.anko.toast

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SearchAdapter(datas: ArrayList<Any>) : BaseAdapter<Any>(datas,
        object : MultiType<Any> {

            override fun layoutId(item: Any, pos: Int): Int = when (item) {
                is Banner -> R.layout.layout_search_item_banner
                is SearchHeader -> R.layout.layout_search_item_header
                else -> R.layout.layout_search_item_banner
            }
        }) {

    override fun bindData(holder: BaseViewHolder, data: Any, position: Int) = when (data) {
        is Banner -> {
            holder.setText(R.id.tv_title, data.title)
            holder.getView<ImageView>(R.id.iv_cover).glide(data.cover)
        }
        is SearchHeader -> {
            data.banners_title?.let {
                val banners_title: List<Banner> = data.banners_title!!.data as List<Banner>
                val banner = holder.getViewGroup<BGABanner>(R.id.banner)
                banner.setAdapter(object : BGABanner.Adapter<ImageView, Banner> {

                    override fun fillBannerItem(banner: BGABanner, itemView: ImageView, model: Banner?, position: Int) {
                        itemView.glide(model?.cover)
                    }
                })
                banner.setDelegate(object : BGABanner.Delegate<ImageView, Banner> {

                    override fun onBannerItemClick(banner: BGABanner, itemView: ImageView, model: Banner?, position: Int) {
                        context.toast(model?.title!!)
                    }
                })
                val tips = arrayListOf<String>()
                banners_title.forEach {
                    tips.add(it.title)
                }
                banner.setData(banners_title, tips)
            }
            data.banners_horizontal?.let {
                val rv_top_questions = holder.getView<RecyclerView>(R.id.rv_top_questions)
                rv_top_questions.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                if (rv_top_questions.onFlingListener == null) {
                    LinearSnapHelper().attachToRecyclerView(rv_top_questions)
                }
                val searchQuestionAdapter = SearchQuestionAdapter(R.layout.layout_search_question_item, data.banners_horizontal!!.data as ArrayList<Banner>)
                rv_top_questions.adapter = searchQuestionAdapter
            }
            data.hot_authors?.let {
                val rv_hot_authors = holder.getViewGroup<RecyclerView>(R.id.rv_hot_authors)
                rv_hot_authors.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                if (rv_hot_authors.onFlingListener == null) {
                    LinearSnapHelper().attachToRecyclerView(rv_hot_authors)
                }
                val searchUserAdapter = SearchUserAdapter(R.layout.layout_search_user_item, data.hot_authors!!.data as ArrayList<User>)
                rv_hot_authors.adapter = searchUserAdapter
            }
        }
        else -> {
        }
    }
}