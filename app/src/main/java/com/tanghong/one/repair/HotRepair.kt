package com.tanghong.one.repair

import android.content.Context
import org.jetbrains.anko.toast

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class HotRepair {

    fun divide(context: Context) {
        val i = 10
        val j = 0

        if (j > 0) {
            context.toast("value = ${i / j}")
        } else {
            context.toast("error must j > 0")
        }
    }
}