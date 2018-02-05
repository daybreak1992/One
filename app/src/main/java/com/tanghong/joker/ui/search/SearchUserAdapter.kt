package com.tanghong.joker.ui.search

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.scwang.smartrefresh.layout.util.DensityUtil
import com.tanghong.commonlibrary.base.adapter.BaseAdapter
import com.tanghong.commonlibrary.base.adapter.BaseViewHolder
import com.tanghong.joker.R
import com.tanghong.joker.glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import model.User

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SearchUserAdapter(layoutId: Int, datas: ArrayList<User>) : BaseAdapter<User>(layoutId, datas) {

    override fun bindData(holder: BaseViewHolder, data: User, position: Int) {
        with(data) {
            holder.setText(R.id.tv_name, user_name)
            holder.setText(R.id.tv_desc, desc)
            holder.getView<ImageView>(R.id.iv_avatar).glide(web_url,
                    RequestOptions.bitmapTransform(RoundedCornersTransformation(DensityUtil.dp2px(30f), 0)))
        }
    }
}