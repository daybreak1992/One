package com.tanghong.commonlibrary.base.adapter

interface OnItemLongClickListener<T> {

    fun onItemLongClick(data: T, position: Int): Boolean
}
