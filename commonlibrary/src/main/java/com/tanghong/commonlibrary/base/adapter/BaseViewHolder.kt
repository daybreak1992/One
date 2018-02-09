package com.tanghong.commonlibrary.base.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var viewArray: SparseArray<View>? = null

    init {
        viewArray = SparseArray()
    }

    fun <T : View> getView(id: Int): T {
        var view: View? = viewArray?.get(id)
        if (view == null) {
            view = itemView.findViewById(id)
            viewArray?.put(id, view)
        }
        return view as T
    }

    fun <T : ViewGroup> getViewGroup(id: Int): T {
        var view: View? = viewArray?.get(id)
        if (view == null) {
            view = itemView.findViewById(id)
            viewArray?.put(id, view)
        }
        return view as T
    }

    fun setText(id: Int, text: CharSequence): BaseViewHolder {
        val view = getView<TextView>(id)
        view.text = text
        return this
    }

    fun setHintText(id: Int, text: CharSequence): BaseViewHolder {
        val view = getView<TextView>(id)
        view.hint = text.toString()
        return this
    }

    fun setImageResource(id: Int, resId: Int): BaseViewHolder {
        val iv = getView<ImageView>(id)
        iv.setImageResource(resId)
        return this
    }

    fun setImagePath(id: Int, imageLoader: BaseImageLoader): BaseViewHolder {
        val iv = getView<ImageView>(id)
        imageLoader.loadImage(iv, imageLoader.path)
        return this
    }

    abstract class BaseImageLoader(val path: String) {

        abstract fun loadImage(iv: ImageView, path: String)
    }

    fun setViewVisibility(id: Int, visibility: Int): BaseViewHolder {
        getView<View>(id).visibility = visibility
        return this
    }

    fun setOnItemClickListener(listener: View.OnClickListener) {
        itemView.setOnClickListener(listener)
    }

    fun setOnItemLongClickListener(listener: View.OnLongClickListener) {
        itemView.setOnLongClickListener(listener)
    }
}