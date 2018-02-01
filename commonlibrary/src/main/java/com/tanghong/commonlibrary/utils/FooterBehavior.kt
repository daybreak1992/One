package com.tanghong.commonlibrary.utils

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class FooterBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean =
            dependency is AppBarLayout

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        val delta: Int? = dependency?.top
        if (delta != null)
            child?.translationY = (-delta).toFloat()
        return true
    }
}