package com.tanghong.joker.ui.main

import android.text.TextUtils
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tanghong.commonlibrary.base.adapter.BaseAdapter
import com.tanghong.commonlibrary.base.adapter.BaseViewHolder
import com.tanghong.commonlibrary.base.adapter.MultiType
import com.tanghong.commonlibrary.utils.ScreenUtils
import com.tanghong.commonlibrary.utils.TimeDateUtils
import com.tanghong.joker.R
import com.tanghong.joker.ui.other.X5WebView
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import model.Comment

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/31
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class DetailAdapter(datas: ArrayList<Any>) : BaseAdapter<Any>(datas,
        object : MultiType<Any> {

            override fun layoutId(item: Any, pos: Int): Int {
                if (item is String) {
                    return R.layout.layout_detail_item_header
                }
                return R.layout.layout_detail_item_comment
            }
        }) {

    private var hasInitWebView: Boolean = false

    override fun bindData(holder: BaseViewHolder, data: Any, position: Int) {
        if (data is String) {
            if (!hasInitWebView) {
                hasInitWebView = true
                val fl_container = holder.getViewGroup<FrameLayout>(R.id.fl_container)
                fl_container.addView(X5WebView.createWebView(context))
                X5WebView.loadDataWithBaseURL("", data, "text/html", "UTF-8", "");
            }
        } else {
            if (data is Comment) {
                with(data) {
                    holder.setText(R.id.tv_name, user.user_name)
                    holder.setText(R.id.tv_time, TimeDateUtils.formatTime(input_date))
                    if (TextUtils.isEmpty(quote)) {
                        holder.setViewVisibility(R.id.tv_replay, View.GONE)
                    } else {
                        holder.setViewVisibility(R.id.tv_replay, View.VISIBLE)
                        holder.setText(R.id.tv_replay, "${touser.user_name}：$quote")
                    }
                    holder.setText(R.id.tv_content, content)
                    holder.setText(R.id.tv_praise, praisenum)
                    if (type == "0") {
                        holder.setViewVisibility(R.id.tv_hot, View.VISIBLE)
                    } else {
                        holder.setViewVisibility(R.id.tv_hot, View.GONE)
                    }
                    Glide.with(context).load(user.web_url).
                            apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(ScreenUtils.dip2px(20f), 0)))
                            .into(holder.getView(R.id.iv_avatar))
                }
            }
        }
    }
}