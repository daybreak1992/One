package com.tanghong.joker.ui.search

import android.support.v7.widget.SearchView
import android.view.Menu
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.joker.R
import kotlinx.android.synthetic.main.layout_toolbar.*

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

    override fun initPresenter(): SearchDetailPresenter = SearchDetailPresenter()

    override fun layoutId(): Int = R.layout.activity_search_detail

    override fun initView() {
        presenter.attachView(this)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBar.setTitle(R.string.title_search)
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
        sv_search_detail = searchItem?.actionView as SearchView?
        initSearchView()
        return true
    }

    private fun initSearchView() {
        //设置搜索框直接展开显示。左侧有放大镜(在搜索框中) 右侧有叉叉 可以关闭搜索框
        //sv_search_detail.setIconified(false);
        //设置搜索框直接展开显示。左侧有放大镜(在搜索框外) 右侧无叉叉 有输入内容后有叉叉 不能关闭搜索框
        //sv_search_detail.setIconifiedByDefault(false);
        //设置搜索框直接展开显示。左侧有无放大镜(在搜索框中) 右侧无叉叉 有输入内容后有叉叉 不能关闭搜索框
        sv_search_detail?.onActionViewExpanded()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}