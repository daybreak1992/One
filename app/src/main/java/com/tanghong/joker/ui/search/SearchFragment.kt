package com.tanghong.joker.ui.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener
import com.tanghong.commonlibrary.base.BaseFragment
import com.tanghong.commonlibrary.base.adapter.OnItemClickListener
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

    var last_id: String = "0"

    val banners_title = "3"
    val banners = "4"
    val banners_horizontal = "5"

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
        searchAdapter?.setOnItemClickListener(object : OnItemClickListener<Any> {

            override fun onItemClick(data: Any, position: Int) {
                if (data is Banner) {
                    toast(data.title)
                }
            }
        })

        srl_search.setRefreshFooter(ClassicsFooter(context))
        srl_search.setRefreshHeader(ClassicsHeader(context))
        srl_search.setOnRefreshLoadmoreListener(object : OnRefreshLoadmoreListener {
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                onLoadMore()
            }

            override fun onRefresh(refreshlayout: RefreshLayout?) {
                onRefresh()
            }
        })
    }

    override fun initData() {

    }

    override fun start() {
        srl_search.autoRefresh()
    }

    fun onRefresh() {
        showProgress()
        last_id = "0"
        presenter.loadHotAuthors()
        presenter.loadBanners(true, banners_title, last_id)
    }

    fun onLoadMore() {
        if (searchList.size > 1) {
            last_id = (searchList.last() as Banner).id
        } else {
            last_id = "0"
        }
        presenter.loadBanners(false, banners, last_id)
    }

    fun onSearchClick() {
        toast("searchClick")
    }

    override fun setBanners(isRefresh: Boolean, type: String, result: Result<List<Banner>>) {
        if (isRefresh) {
            if (type == banners_title) {
                presenter.loadBanners(true, banners, last_id)
            } else {
                closeProgress()
                srl_search.finishRefresh()
            }
        } else {
            closeProgress()
            if (result.data.isEmpty()) {
                srl_search.finishLoadmoreWithNoMoreData()
            } else {
                srl_search.finishLoadmore()
            }
        }
        if (result.data.isEmpty()) {
            return
        }
        if (type == banners_title) {
            searchList.clear()
            searchList.add(result)
            searchAdapter?.notifyDataSetChanged()
        } else {
            searchList.addAll(result.data)
            searchAdapter?.notifyItemRangeInserted(searchList.size, result.data.size)
        }
    }

    override fun setHotAuthors(result: Result<List<User>>) {

    }

    override fun setError(isRefresh: Boolean, e: ApiException) {
        closeProgress()
        if (isRefresh) {
            srl_search.finishRefresh(false)
        } else {
            srl_search.finishLoadmore(false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}