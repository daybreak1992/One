package com.tanghong.commonlibrary.base

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import com.tanghong.commonlibrary.utils.ScreenUtils

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */
abstract class BaseDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(true)
        val lp = dialog.window.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window.attributes = lp
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.window.setElevation(ScreenUtils.dip2px(4.0f).toFloat())
        }
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(layoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        start()
    }

    abstract fun layoutId(): Int

    abstract fun initView()

    abstract fun initData()

    abstract fun start()
}