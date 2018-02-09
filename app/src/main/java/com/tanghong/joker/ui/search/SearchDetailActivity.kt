package com.tanghong.joker.ui.search

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.commonlibrary.base.adapter.OnItemClickListener
import com.tanghong.joker.R
import com.tanghong.joker.openPage
import com.tanghong.joker.ui.profile.CategoryActivity
import kotlinx.android.synthetic.main.activity_search_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import model.Content

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
    val searchLabels = arrayListOf<String>(Content.category_illustration, Content.category_article,
            Content.category_serial, Content.category_questions_answers, Content.category_music,
            Content.category_movie, Content.category_radio)
    var searchLabelAdapter: SearchLabelAdapter? = null

    override fun initPresenter(): SearchDetailPresenter = SearchDetailPresenter()

    override fun layoutId(): Int = R.layout.activity_search_detail

    override fun initView() {
        presenter.attachView(this)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBar.setTitle(R.string.title_search)

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

        toolBar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initData() {

    }

    override fun start() {

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
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}