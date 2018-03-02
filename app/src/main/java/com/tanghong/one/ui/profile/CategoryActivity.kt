package com.tanghong.one.ui.profile

import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.one.R
import com.tanghong.one.ui.other.BridgeWebViewFactory
import http.ApiException
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import model.Category
import model.Content

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CategoryActivity : BaseActivity<CategoryPresenter>(), CategoryContract.View {

    var id: String = ""

    override fun initPresenter(): CategoryPresenter = CategoryPresenter()

    override fun layoutId(): Int = R.layout.activity_category

    override fun initView() {
        presenter.attachView(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        rl_container.addView(BridgeWebViewFactory.createWebView(this))
        srl_category.isEnableLoadmore = false
        srl_category.refreshHeader = ClassicsHeader(this)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        srl_category.setOnRefreshLoadmoreListener(object : OnRefreshLoadmoreListener {
            override fun onLoadmore(refreshlayout: RefreshLayout?) {

            }

            override fun onRefresh(refreshlayout: RefreshLayout?) {
                onRefresh()
            }
        })
    }

    override fun initData() {
        id = intent.getStringExtra("id")
        when (id) {
            Content.illustration.toString() -> supportActionBar?.title = Content.category_illustration
            Content.article.toString() -> supportActionBar?.title = Content.category_article
            Content.questions_answers.toString() -> supportActionBar?.title = Content.category_questions_answers
            Content.serial.toString() -> supportActionBar?.title = Content.category_serial
            Content.music.toString() -> supportActionBar?.title = Content.category_music
            Content.movie.toString() -> supportActionBar?.title = Content.category_movie
            Content.radio.toString() -> supportActionBar?.title = Content.category_radio
            else -> {

            }
        }
    }

    override fun start() {
        srl_category.autoRefresh()
    }

    fun onRefresh() {
        showProgress()
        presenter.loadCategory(id)
    }

    override fun setCategory(result: Category) {
        closeProgress()
        srl_category.finishRefresh()
        BridgeWebViewFactory.loadDataWithBaseURL("", result.html_content, "text/html", "UTF-8", "");
    }

    override fun setError(e: ApiException) {
        closeProgress()
        srl_category.finishRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        BridgeWebViewFactory.destroyWebView()
    }
}