package com.tanghong.joker.ui.search

import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import com.tanghong.commonlibrary.base.adapter.BaseAdapter
import com.tanghong.commonlibrary.base.adapter.BaseViewHolder
import com.tanghong.commonlibrary.base.adapter.MultiType
import com.tanghong.joker.R
import com.tanghong.joker.glide
import model.Banner
import model.Result
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
                is Result<*> -> R.layout.layout_search_item_header
                else -> R.layout.layout_search_item_banner
            }
        }) {

    override fun bindData(holder: BaseViewHolder, data: Any, position: Int) = when (data) {
        is Banner -> {
            holder.setText(R.id.tv_title, data.title)
            holder.getView<ImageView>(R.id.iv_cover).glide(data.cover)
        }
        is Result<*> -> {
            val banners_title: List<Banner> = data.data as List<Banner>
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
        else -> {

        }
    }
}