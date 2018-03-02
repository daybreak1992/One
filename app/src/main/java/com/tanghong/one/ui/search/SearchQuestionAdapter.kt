package com.tanghong.one.ui.search

import android.widget.ImageView
import com.tanghong.commonlibrary.base.adapter.BaseAdapter
import com.tanghong.commonlibrary.base.adapter.BaseViewHolder
import com.tanghong.one.R
import com.tanghong.one.glide
import model.Banner

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SearchQuestionAdapter(layoutId: Int, datas: ArrayList<Banner>) : BaseAdapter<Banner>(layoutId, datas) {

    override fun bindData(holder: BaseViewHolder, data: Banner, position: Int) {
        with(data) {
            holder.setText(R.id.tv_title, title)
            holder.getView<ImageView>(R.id.iv_cover).glide(cover)
        }
    }
}