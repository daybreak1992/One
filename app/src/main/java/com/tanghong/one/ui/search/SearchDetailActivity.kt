package com.tanghong.one.ui.search

import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.view.Menu
import android.view.View
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.commonlibrary.base.adapter.BasePagerAdapter
import com.tanghong.commonlibrary.base.adapter.OnItemClickListener
import com.tanghong.one.R
import com.tanghong.one.openPage
import com.tanghong.one.ui.main.DetailActivity
import com.tanghong.one.ui.profile.CategoryActivity
import http.ApiException
import kotlinx.android.synthetic.main.activity_search_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import model.Content
import model.Result
import model.Search
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SearchDetailActivity : BaseActivity<SearchDetailPresenter>(), SearchDetailContract.View {

    var sv_search_detail: SearchView? = null
    val searchLabelsMap = hashMapOf<String, String>(
            Content.tag_illustration to Content.category_illustration,
            Content.tag_reading to Content.category_article,
            Content.tag_music to Content.category_music,
            Content.tag_movie to Content.category_movie,
            Content.tag_radio to Content.category_radio,
            Content.tag_author to Content.category_author
    )
    val searchLabels = arrayListOf<String>(Content.category_illustration, Content.category_article,
            Content.category_serial, Content.category_questions_answers, Content.category_music,
            Content.category_movie, Content.category_radio)
    var searchLabelAdapter: SearchLabelAdapter? = null
    val pages = arrayListOf<View>()

    override fun initPresenter(): SearchDetailPresenter = SearchDetailPresenter()

    override fun layoutId(): Int = R.layout.activity_search_detail

    override fun initView() {
        presenter.attachView(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setTitle(R.string.title_search)

        rv_search_detail.layoutManager = LinearLayoutManager(this)
        searchLabelAdapter = SearchLabelAdapter(R.layout.layout_search_label_item, searchLabels)
        rv_search_detail.adapter = searchLabelAdapter
        searchLabelAdapter?.setOnItemClickListener(object : OnItemClickListener<String> {

            override fun onItemClick(data: String, position: Int) {
                var id = ""
                when (data) {
                    Content.category_illustration -> id = Content.illustration.toString()
                    Content.category_article -> id = Content.article.toString()
                    Content.category_serial -> id = Content.serial.toString()
                    Content.category_questions_answers -> id = Content.questions_answers.toString()
                    Content.category_music -> id = Content.music.toString()
                    Content.category_movie -> id = Content.movie.toString()
                    Content.category_radio -> id = Content.radio.toString()
                }
                openPage(CategoryActivity::class.java, hashMapOf<String, String>(
                        "id" to id
                ))
            }
        })

        for (i in 0 until searchLabelsMap.values.size) {
            val page = layoutInflater.inflate(R.layout.layout_search_page_item, vp_search_tab, false)
            val srl_search_page_item = page.find<SmartRefreshLayout>(R.id.srl_search_page_item)
            srl_search_page_item.setRefreshHeader(ClassicsHeader(this))
            srl_search_page_item.setRefreshFooter(ClassicsFooter(this))
            srl_search_page_item.setOnRefreshLoadmoreListener(RefreshLoadMoreListener())

            val rv_search_page_item = page.find<RecyclerView>(R.id.rv_search_page_item)
            rv_search_page_item.layoutManager = LinearLayoutManager(this)
            val adapter = SearchDetailPagerItemAdapter(R.layout.layout_search_page_list_item)
            adapter.setOnItemClickListener(object : OnItemClickListener<Content> {

                override fun onItemClick(data: Content, position: Int) {
                    openPage(DetailActivity::class.java, hashMapOf<String, String>(
                            "id" to data.content_id,
                            "category" to data.category
                    ))
                }
            })
            rv_search_page_item.adapter = adapter

            pages.add(page)
        }
        val pagerAdapter = BasePagerAdapter(pages, ArrayList(searchLabelsMap.values))
        vp_search_tab.adapter = pagerAdapter
        tl_search_detail.setupWithViewPager(vp_search_tab)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        vp_search_tab.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if ((pages[position].find<RecyclerView>(R.id.rv_search_page_item).adapter
                                as SearchDetailPagerItemAdapter).datas.isEmpty()) {
                    pages[position].find<SmartRefreshLayout>(R.id.srl_search_page_item).autoRefresh()
                }
            }
        })
    }

    override fun initData() {

    }

    override fun start() {

    }

    override fun setSearchWord(isRefresh: Boolean, result: Result<Search>) {
        closeProgress()
        rv_search_detail.visibility = View.GONE
        if (isRefresh) {
            pages[vp_search_tab.currentItem].find<SmartRefreshLayout>(R.id.srl_search_page_item).finishRefresh()
        } else {
            if (result.data.list.isEmpty()) {
                pages[vp_search_tab.currentItem].find<SmartRefreshLayout>(R.id.srl_search_page_item).finishLoadmoreWithNoMoreData()
            } else {
                pages[vp_search_tab.currentItem].find<SmartRefreshLayout>(R.id.srl_search_page_item).finishLoadmore()
            }
        }
        (pages[vp_search_tab.currentItem].find<RecyclerView>(R.id.rv_search_page_item).adapter as SearchDetailPagerItemAdapter)
                .refreshData(isRefresh, result.data.list as ArrayList<Content>)
    }

    override fun setError(isRefresh: Boolean, e: ApiException) {
        closeProgress()
        if (isRefresh) {
            pages[vp_search_tab.currentItem].find<SmartRefreshLayout>(R.id.srl_search_page_item).finishRefresh()
        } else {
            pages[vp_search_tab.currentItem].find<SmartRefreshLayout>(R.id.srl_search_page_item).finishLoadmore()
        }
    }

    inner class RefreshLoadMoreListener : OnRefreshLoadmoreListener {
        var page = 1

        override fun onLoadmore(refreshlayout: RefreshLayout?) {
            page++
            loadSearchWordData(false, page)
        }

        override fun onRefresh(refreshlayout: RefreshLayout?) {
            page = 1
            loadSearchWordData(true, page)
        }
    }

    fun loadSearchWordData(isRefresh: Boolean, page: Int) {
        showProgress()
        presenter.loadSearchWord(isRefresh, getCurrentType(), sv_search_detail?.query.toString(), page)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchItem = menu?.findItem(R.id.menu_search_detail)
        sv_search_detail = searchItem?.actionView as SearchView
        initSearchView()
        return true
    }

    private fun initSearchView() {
        //设置搜索框直接展开显示。左侧有放大镜(在搜索框中) 右侧有叉叉 可以关闭搜索框
//        sv_search_detail?.setIconified(false)
        //设置搜索框直接展开显示。左侧有放大镜(在搜索框外) 右侧无叉叉 有输入内容后有叉叉 不能关闭搜索框
//        sv_search_detail?.setIconifiedByDefault(false)
        //设置搜索框直接展开显示。左侧有无放大镜(在搜索框中) 右侧无叉叉 有输入内容后有叉叉 不能关闭搜索框
        sv_search_detail?.onActionViewExpanded()
        //搜索框展开时后面叉叉按钮的点击事件
        sv_search_detail?.setOnCloseListener {
            false
        }
        //搜索图标按钮(打开搜索框的按钮)的点击事件
        sv_search_detail?.setOnSearchClickListener { v ->

        }
        //搜索框文字变化监听
        sv_search_detail?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (TextUtils.isEmpty(query)) {
                    toast(R.string.prompt_input_content)
                } else {
                    pages[vp_search_tab.currentItem].find<SmartRefreshLayout>(R.id.srl_search_page_item).autoRefresh()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (TextUtils.isEmpty(newText)) {
                    rv_search_detail.visibility = View.VISIBLE
                }
                return false
            }
        })
    }

    fun getCurrentType(): String {
        val value = tl_search_detail.getTabAt(vp_search_tab.currentItem)?.text
        searchLabelsMap.map {














            if (value == it.value) {
                return it.key
            }
        }
        return ""
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}