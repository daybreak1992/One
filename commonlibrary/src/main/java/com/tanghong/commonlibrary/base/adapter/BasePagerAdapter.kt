package com.tanghong.commonlibrary.base.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BasePagerAdapter(val datas: ArrayList<View>, val titles: ArrayList<String>?) : PagerAdapter() {

    constructor(datas: ArrayList<View>) : this(datas, null)

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun getCount(): Int = datas.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(datas[position])
        return datas[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }

    override fun getPageTitle(position: Int): CharSequence? = titles?.get(position)
}