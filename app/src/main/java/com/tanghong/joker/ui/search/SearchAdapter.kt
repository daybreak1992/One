package com.tanghong.joker.ui.search

import com.tanghong.commonlibrary.base.adapter.BaseAdapter
import com.tanghong.commonlibrary.base.adapter.BaseViewHolder
import com.tanghong.commonlibrary.base.adapter.MultiType
import com.tanghong.joker.R

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

            override fun layoutId(item: Any, pos: Int): Int {
                return R.layout.layout_search_item_header
            }
        }) {

    override fun bindData(holder: BaseViewHolder, data: Any, position: Int) {

    }
}