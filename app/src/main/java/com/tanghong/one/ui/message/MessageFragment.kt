package com.tanghong.one.ui.message

import android.os.Bundle
import com.tanghong.commonlibrary.base.BaseFragment
import com.tanghong.one.R

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class MessageFragment : BaseFragment<MessagePresenter>() {

    companion object {

        fun getInstance(): MessageFragment {
            val instance = MessageFragment()
            val bundle = Bundle()
            instance.arguments = bundle
            return instance
        }
    }

    override fun initPresenter(): MessagePresenter = MessagePresenter()

    override fun layoutId(): Int = R.layout.fragment_message

    override fun initView() {

    }

    override fun initData() {

    }

    override fun start() {

    }
}