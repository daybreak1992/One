package com.tanghong.joker.ui.search

import com.tanghong.commonlibrary.base.adapter.BaseAdapter
import com.tanghong.commonlibrary.base.adapter.BaseViewHolder
import com.tanghong.joker.R


/**
 * Created by hasee on 2018/2/9.
 */
class SearchLabelAdapter(layoutId: Int, datas: ArrayList<String>) : BaseAdapter<String>(layoutId, datas) {

    override fun bindData(holder: BaseViewHolder, data: String, position: Int) {
        holder.setText(R.id.tv_label, data)
    }
}