package com.tanghong.one.ui.main

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.tanghong.commonlibrary.base.adapter.BaseAdapter
import com.tanghong.commonlibrary.base.adapter.BaseViewHolder
import com.tanghong.commonlibrary.base.adapter.MultiType
import com.tanghong.commonlibrary.utils.ScreenUtils
import com.tanghong.commonlibrary.utils.TimeDateUtils
import com.tanghong.one.R
import com.tanghong.one.glide
import com.tanghong.one.openPage
import com.tanghong.one.ui.other.BridgeWebViewFactory
import com.tanghong.one.ui.profile.UserInfoActivity
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

            override fun layoutId(item: Any, pos: Int): Int = when (item) {
                is String -> R.layout.layout_detail_item_header
                is Comment -> R.layout.layout_detail_item_comment
                else -> R.layout.layout_detail_item_comment
            }
        }) {

    private var hasInitWebView: Boolean = false

    override fun bindData(holder: BaseViewHolder, data: Any, position: Int) {
        when (data) {
            is String -> {
                if (!hasInitWebView) {
                    hasInitWebView = true
                    val fl_container = holder.getViewGroup<FrameLayout>(R.id.fl_container)
                    fl_container.addView(BridgeWebViewFactory.createWebView(context))
                    BridgeWebViewFactory.getBridgeWebView()?.registerHandler("prepareAudios",
                            { data, function ->
                                Log.i("detail", "prepareAudios = $data")
                            })
                    BridgeWebViewFactory.getBridgeWebView()?.registerHandler("prepareAudios", { data, function ->
                        Log.i("webView", "prepareAudios = $data")
                    })
                    BridgeWebViewFactory.getBridgeWebView()?.registerHandler("playAudio", { data, function ->
                        Log.i("webView", "playAudio = $data")
                    })
                    BridgeWebViewFactory.getBridgeWebView()?.registerHandler("playRadio", { data, function ->
                        Log.i("webView", "playRadio = $data")
                    })
                    BridgeWebViewFactory.getBridgeWebView()?.registerHandler("playMusic", { data, function ->
                        Log.i("webView", "playMusic = $data")
                    })
                    BridgeWebViewFactory.getBridgeWebView()?.registerHandler("followAuthor", { data, function ->
                        Log.i("webView", "followAuthor = $data")
                    })
                    BridgeWebViewFactory.loadDataWithBaseURL("", data, "text/html",
                            "utf-8", "");
                }
            }
            is Comment -> {
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
                    holder.getView<ImageView>(R.id.iv_avatar).glide(user.web_url,
                            RequestOptions.bitmapTransform(RoundedCornersTransformation(ScreenUtils.dip2px(20f), 0)))
                    holder.getView<ImageView>(R.id.iv_avatar).setOnClickListener {
                        context.openPage(UserInfoActivity::class.java, hashMapOf<String, String>(
                                "id" to user.user_id
                        ))
                    }
                }
            }
            else -> {

            }
        }
    }
}