package com.tanghong.one.ui.other

import com.tanghong.commonlibrary.base.IBaseView
import com.tanghong.commonlibrary.base.IPresenter

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface SurfaceViewContract {

    interface View : IBaseView {

    }

    interface Presenter : IPresenter<View> {

    }
}