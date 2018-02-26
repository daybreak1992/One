package com.tanghong.joker.ui.profile

import com.tanghong.commonlibrary.base.IBaseView
import com.tanghong.commonlibrary.base.IPresenter

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface SettingContract {

    interface View : IBaseView {

        fun setCacheSize(size: String)

        fun setClearCache(success: Boolean)
    }

    interface Presenter : IPresenter<View> {

        fun getCacheSize()

        fun clearCache()
    }
}