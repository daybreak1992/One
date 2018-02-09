package com.tanghong.joker

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import source.RepositoryFactory
import source.TasksRepository
import java.io.Serializable

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

fun <T> Context.openPage(toClass: Class<T>) = openPage(toClass, null)

fun <T> Context.openPage(toClass: Class<T>, params: HashMap<String, *>?) {
    val intent = Intent(this, toClass)
    if (params != null && !params.isEmpty()) {
        val extras = Bundle()
        for ((key, value) in params) {
            when (value) {
                is String -> extras.putString(key, value)
                is Int -> extras.putInt(key, value)
                is Float -> extras.putFloat(key, value)
                is Double -> extras.putDouble(key, value)
                is Long -> extras.putLong(key, value)
                is Parcelable -> extras.putParcelable(key, value)
                is Serializable -> extras.putSerializable(key, value)
            }
        }
        intent.putExtras(extras)
    }
    startActivity(intent)
}

fun Context.openWeb(url: String) {
    if (TextUtils.isEmpty(url)) return
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

fun createRepository(): TasksRepository = RepositoryFactory.provideRepository()

fun ImageView.glide(url: String?) {
    if (TextUtils.isEmpty(url)) {
        return
    }
    Glide.with(context)
            .load(url)
            .into(this)
}

fun ImageView.glide(url: String?, options: RequestOptions) {
    if (TextUtils.isEmpty(url)) {
        return
    }
    Glide.with(context)
            .load(url)
            .apply(options)
            .into(this)
}

fun ImageView.glide(url: String?, listener: RequestListener<Drawable>) {
    if (TextUtils.isEmpty(url)) {
        return
    }
    Glide.with(context)
            .load(url)
            .listener(listener)
            .into(this)
}

fun ImageView.glide(url: String?, options: RequestOptions, listener: RequestListener<Drawable>) {
    if (TextUtils.isEmpty(url)) {
        return
    }
    Glide.with(context)
            .load(url)
            .apply(options)
            .listener(listener)
            .into(this)
}

fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).show()
}