package com.tanghong.commonlibrary.base.adapter

interface OnItemClickListener<in T> {

    fun onItemClick(data: T, position: Int)
}
