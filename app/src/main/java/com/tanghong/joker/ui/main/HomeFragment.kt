package com.tanghong.joker.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener
import com.tanghong.commonlibrary.base.BaseFragment
import com.tanghong.commonlibrary.base.adapter.OnItemClickListener
import com.tanghong.joker.R
import com.tanghong.joker.app.Constants
import com.tanghong.joker.openPage
import http.ApiException
import kotlinx.android.synthetic.main.fragment_home.*
import model.Content
import model.One
import model.Result
import java.text.SimpleDateFormat
import java.util.*

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class HomeFragment : BaseFragment<HomePresenter>(), HomeContract.View {

    val contentList = arrayListOf<Content>()
    var homeAdapter: HomeAdapter? = null
    var date: String? = null

    companion object {

        fun getInstance(): HomeFragment {
            val instance = HomeFragment()
            val bundle = Bundle()
            instance.arguments = bundle
            return instance
        }
    }

    override fun initPresenter(): HomePresenter = HomePresenter()

    override fun layoutId(): Int = R.layout.fragment_home

    override fun initView() {
        presenter.attachView(this)
        homeAdapter = HomeAdapter(contentList)
        rv_home.layoutManager = LinearLayoutManager(activity)
        rv_home.adapter = homeAdapter
        srl_home.isEnableLoadmore = false
        srl_home.setRefreshHeader(ClassicsHeader(context))

        srl_home.setOnRefreshLoadmoreListener(object : OnRefreshLoadmoreListener {

            override fun onLoadmore(refreshlayout: RefreshLayout?) {

            }

            override fun onRefresh(refreshlayout: RefreshLayout?) {
                onRefresh()
            }
        })
        homeAdapter?.setOnItemClickListener(object : OnItemClickListener<Content> {

            override fun onItemClick(data: Content, position: Int) {
                if (data.category.toInt() != Content.illustration) {
                    val params = hashMapOf<String, Any>(
                            "id" to data.item_id,
                            "category" to data.category,
                            "source_id" to data.id
                    )
                    context?.openPage(DetailActivity::class.java, params)
                }
            }
        })
    }

    override fun initData() {

    }

    override fun start() {
        srl_home.autoRefresh()
    }

    fun onRefresh() {
        showProgress()
        presenter.loadOne(date ?: SimpleDateFormat("yyyy-MM-dd").format(Date()), Constants.city)
    }

    fun selectDateRefresh(selectDate: String) {
        date = selectDate
        rv_home.scrollToPosition(0)
        srl_home.autoRefresh()
    }

    override fun setOne(result: Result<One>) {
        closeProgress()
        srl_home.finishRefresh()
        contentList.clear()
        contentList.addAll(result.data.content_list)
        homeAdapter?.notifyDataSetChanged()
    }

    override fun setError(e: ApiException) {
        closeProgress()
        srl_home.finishRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}