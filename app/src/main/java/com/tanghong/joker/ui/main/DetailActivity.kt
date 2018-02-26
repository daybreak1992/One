package com.tanghong.joker.ui.main

import android.graphics.PixelFormat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.commonlibrary.utils.JsonUtils
import com.tanghong.joker.R
import com.tanghong.joker.openPage
import com.tanghong.joker.ui.other.BridgeWebViewFactory
import com.tanghong.joker.ui.other.WebViewActivity
import http.ApiException
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import model.*

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class DetailActivity : BaseActivity<DetailPresenter>(), DetailContract.View {

    var id = ""
    var category = ""
    var source_id = ""

    val detailList = arrayListOf<Any>()
    var detailAdapter: DetailAdapter? = null

    override fun initPresenter(): DetailPresenter = DetailPresenter()

    override fun layoutId(): Int = R.layout.activity_detail

    override fun initView() {
        window.setFormat(PixelFormat.TRANSLUCENT)
        presenter.attachView(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        rv_detail.layoutManager = LinearLayoutManager(this)
        detailAdapter = DetailAdapter(detailList)
        rv_detail.adapter = detailAdapter
        srl_detail.setRefreshHeader(ClassicsHeader(this))
        srl_detail.setRefreshFooter(ClassicsFooter(this))

        toolbar.setNavigationOnClickListener {
            finish()
        }
        srl_detail.setOnRefreshLoadmoreListener(object : OnRefreshLoadmoreListener {

            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                onLoadMore()
            }

            override fun onRefresh(refreshlayout: RefreshLayout?) {
                onRefresh()
            }
        })
        btn_web.setOnClickListener {
            openPage(WebViewActivity::class.java, hashMapOf<String, String>(
                    "web_data" to detailList[0] as String
            ))
        }
    }

    override fun initData() {
        id = intent.getStringExtra("id")
        val type = intent.getStringExtra("category")
        source_id = intent.getStringExtra("source_id") ?: ""

        when (type) {
            Content.article.toString() -> category = Content.essay_detail
            Content.questions_answers.toString() -> category = Content.question_detail
            Content.serial.toString() -> category = Content.serialcontent_detail
            Content.music.toString() -> category = Content.music_detail
            Content.movie.toString() -> category = Content.movie_detail
            Content.topic.toString() -> category = Content.topic_detail
        }
    }

    override fun start() {
        srl_detail.autoRefresh()
    }

    fun onRefresh() {
        showProgress()
        presenter.loadDetail(category, id, source_id)
    }

    fun onLoadMore() {
        if (detailList.size <= 1) {
            return
        }
        showProgress()
        presenter.loadDetailComments(false, category, id, (detailList.last() as Comment).id)
    }

    override fun setDetailData(result: Result<Detail>) {
        toolbar.title = result.data.title
        detailList.clear()
        detailList.add(result.data.html_content)
        detailAdapter?.notifyDataSetChanged()

        presenter.loadDetailComments(true, category, id)
    }

    override fun setDetailComments(isRefresh: Boolean, result: Result<CommentRoot>) {
        closeProgress()
        if (isRefresh) {
            srl_detail.finishRefresh()

            val commentRoot = CommentRoot(result.data.data.subList(0, 1).size.toString(),
                    result.data.data.subList(0, 1), result.data.data.subList(0, 1))
            Log.i("detail", JsonUtils.serializeToJson(commentRoot))
            BridgeWebViewFactory.getBridgeWebView()?.callHandler("setComments",
                    JsonUtils.serializeToJson(commentRoot),
                    { data ->
                        Log.i("detail", data)
                    })
        } else {
            if (result.data.data.isEmpty()) {
                srl_detail.finishLoadmoreWithNoMoreData()
            } else {
                srl_detail.finishLoadmore()
            }
        }
        if (result.data.data.isEmpty()) return
        detailList.addAll(result.data.data)
        detailAdapter?.notifyItemRangeInserted(detailList.size, result.data.data.size)
    }

    override fun setError(isRefresh: Boolean, e: ApiException) {
        closeProgress()
        if (isRefresh) {
            srl_detail.finishRefresh()
        } else {
            srl_detail.finishLoadmore(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        BridgeWebViewFactory.destroyWebView()
    }
}