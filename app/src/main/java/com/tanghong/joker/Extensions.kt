package com.tanghong.joker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
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

fun <T> Context.openPage(toClass: Class<T>, params: Map<String, Any>?) {
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

fun createRepository(): TasksRepository = RepositoryFactory.provideRepository()