package com.tanghong.commonlibrary.base.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BaseFragmentPagerAdapter(fm: FragmentManager, val datas: ArrayList<Fragment>, val titles: ArrayList<String>?) : FragmentPagerAdapter(fm) {

    constructor(fm: FragmentManager, datas: ArrayList<Fragment>) : this(fm, datas, null)

    override fun getItem(position: Int): Fragment = datas[position]

    override fun getItemPosition(obj: Any): Int {
        if (datas.contains(obj)) {
            return datas.indexOf(obj)
        }
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int = datas.size

    override fun getPageTitle(position: Int): CharSequence? = titles?.get(position)
}