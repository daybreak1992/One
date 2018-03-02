package com.tanghong.one.ui.search

import android.widget.ImageView
import com.tanghong.commonlibrary.base.adapter.BaseAdapter
import com.tanghong.commonlibrary.base.adapter.BaseViewHolder
import com.tanghong.one.R
import com.tanghong.one.glide
import model.Content

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SearchDetailPagerItemAdapter(layoutId: Int) : BaseAdapter<Content>(layoutId, arrayListOf()) {

    override fun bindData(holder: BaseViewHolder, data: Content, position: Int) {
        with(data) {
            holder.setText(R.id.tv_title, title)
            holder.setText(R.id.tv_subtitle, subtitle)
            holder.getView<ImageView>(R.id.iv_cover).glide(cover)
        }
    }

    fun refreshData(isRefresh: Boolean, contents: ArrayList<Content>) {
        if (isRefresh) {
            datas.clear()
            datas.addAll(contents)
            notifyDataSetChanged()
        } else {
            datas.addAll(contents)
            notifyItemRangeInserted(datas.size, contents.size)
        }
    }
}