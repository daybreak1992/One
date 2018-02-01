package com.tanghong.commonlibrary.base.adapter

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface MultiType<in T> {

    fun layoutId(item: T, pos: Int): Int
}