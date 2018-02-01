package com.tanghong.joker.ui.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener
import com.tanghong.commonlibrary.base.BaseFragment
import com.tanghong.joker.R
import http.ApiException
import kotlinx.android.synthetic.main.fragment_search.*
import model.Banner
import model.Result
import model.User
import org.jetbrains.anko.support.v4.toast

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SearchFragment : BaseFragment<SearchPresenter>(), SearchContract.View {

    val searchList = arrayListOf<Any>()
    var searchAdapter: SearchAdapter? = null

    companion object {

        fun getInstance(): SearchFragment {
            val instance = SearchFragment()
            val bundle = Bundle()
            instance.arguments = bundle
            return instance
        }
    }

    override fun initPresenter(): SearchPresenter = SearchPresenter()

    override fun layoutId(): Int = R.layout.fragment_search

    override fun initView() {
        presenter.attachView(this)
        rv_search.layoutManager = LinearLayoutManager(context)
        searchAdapter = SearchAdapter(searchList)
        rv_search.adapter = searchAdapter

        srl_search.setRefreshFooter(ClassicsFooter(context))
        srl_search.setOnRefreshLoadmoreListener(object : OnRefreshLoadmoreListener {
            override fun onLoadmore(refreshlayout: RefreshLayout?) {

            }

            override fun onRefresh(refreshlayout: RefreshLayout?) {

            }
        })
    }

    override fun initData() {

    }

    override fun start() {

    }

    fun onSearchClick() {
        toast("searchClick")
    }

    override fun setBanners(isRefresh: Boolean, type: String, result: Result<List<Banner>>) {

    }

    override fun setHotAuthors(result: Result<List<User>>) {

    }

    override fun setError(e: ApiException) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}