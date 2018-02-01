package com.tanghong.commonlibrary.utils

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class ScreenUtils private constructor() {

    init {
        throw Throwable("Not allowed to initialize.")
    }

    companion object {
        //design ui
        val design_width = 360
        val design_height = 640

        /**
         * 获取屏幕的宽度（单位：px）
         *
         * @return 屏幕宽px
         */
        fun screenWidth(): Int {
            val windowManager = AppUtils.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()// 创建了一张白纸
            windowManager.defaultDisplay.getMetrics(dm)// 给白纸设置宽高
            return dm.widthPixels
        }

        /**
         * 获取屏幕的高度（单位：px）
         *
         * @return 屏幕高px
         */
        fun screenHeight(): Int {
            val windowManager = AppUtils.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()// 创建了一张白纸
            windowManager.defaultDisplay.getMetrics(dm)// 给白纸设置宽高
            return dm.heightPixels
        }

        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        fun dip2px(dpValue: Float): Int {
            val scale = AppUtils.context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
         */
        fun px2dip(pxValue: Float): Int {
            val scale = AppUtils.context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }

        fun realWidth(px: Int): Int = realWidth(px, design_width)

        fun realWidth(px: Int, design_width: Int): Int = px / design_width * screenWidth()

        fun realHeight(px: Int): Int = realHeight(px, design_height)

        fun realHeight(px: Int, design_height: Int) = px / design_height * screenHeight()

        fun hideSystemUI(view: View) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                view.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }

        fun showSystemUI(view: View) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                view.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
    }
}