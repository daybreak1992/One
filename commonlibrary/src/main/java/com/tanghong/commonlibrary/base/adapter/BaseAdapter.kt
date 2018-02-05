package com.tanghong.commonlibrary.base.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlin.properties.Delegates

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
abstract class BaseAdapter<T>(private var layoutId: Int, var datas: ArrayList<T>) : RecyclerView.Adapter<BaseViewHolder>() {
    private var inflater: LayoutInflater? = null
    protected var context: Context by Delegates.notNull<Context>()
    private var multiType: MultiType<T>? = null

    private var itemClickListener: OnItemClickListener<T>? = null
    private var itemLongClickListener: OnItemLongClickListener<T>? = null

    constructor(datas: ArrayList<T>, multiType: MultiType<T>) : this(-1, datas) {
        this.multiType = multiType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        this.context = parent.context
        if (inflater == null) {
            inflater = LayoutInflater.from(context)
        }
        if (multiType != null) {
            layoutId = viewType
        }
        return BaseViewHolder(inflater?.inflate(layoutId, parent, false)!!)
    }

    override fun getItemViewType(position: Int): Int =
            multiType?.layoutId(datas[position], position) ?: super.getItemViewType(position)

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bindData(holder, datas[position], position)
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener {
                itemClickListener!!.onItemClick(datas[position], position)
            }
        }
        if (itemLongClickListener != null) {
            holder.itemView.setOnLongClickListener {
                itemLongClickListener!!.onItemLongClick(datas[position], position)
            }
        }
    }

    protected abstract fun bindData(holder: BaseViewHolder, data: T, position: Int): Unit?

    fun setOnItemClickListener(itemClickListener: OnItemClickListener<T>) {
        this.itemClickListener = itemClickListener
    }

    fun setOnItemLongClickListener(itemLongClickListener: OnItemLongClickListener<T>) {
        this.itemLongClickListener = itemLongClickListener
    }
}