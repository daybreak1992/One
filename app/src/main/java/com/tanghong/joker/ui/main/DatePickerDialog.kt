package com.tanghong.joker.ui.main

import com.tanghong.commonlibrary.base.BaseDialogFragment
import com.tanghong.joker.R
import kotlinx.android.synthetic.main.layout_date.*
import java.text.SimpleDateFormat

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class DatePickerDialog : BaseDialogFragment() {

    override fun layoutId(): Int = R.layout.layout_date

    override fun initView() {
        btn_confirm.setOnClickListener {
            callback?.apply {
                onSelectedDate("${datePicker.year}-${datePicker.month + 1}-${datePicker.dayOfMonth}")
            }
        }
    }

    override fun initData() {
        datePicker.minDate = SimpleDateFormat("yyyy-MM-dd").parse("2012-10-07").time
        datePicker.maxDate = System.currentTimeMillis()
    }

    override fun start() {

    }

    private var callback: OnDateCallback? = null

    interface OnDateCallback {

        fun onSelectedDate(date: String)
    }

    fun setOnDateCallback(callback: OnDateCallback) {
        this.callback = callback
    }
}