package com.tanghong.commonlibrary.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * Created by tanghong on 2018/3/27.
 */
class CustomViewGroup(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    constructor(context: Context?) : this(context, null)

    init {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //将所有的子View进行测量，这会触发每个子View的onMeasure函数
        //注意要与measureChild区分，measureChild是对单个view进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if (childCount == 0) {
            measureChildren(0, 0)
        } else {
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(getMaxWidth(), getTotalHeight())
            } else if (widthMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(getMaxWidth(), heightSize)
            } else if (heightMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(widthSize, getTotalHeight())
            }
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var topY = t
        for (i in 0 until childCount - 1) {
            val child = getChildAt(i)
            child.layout(l, topY, r + child.measuredWidth, topY + child.measuredHeight)
            topY += child.measuredHeight
        }
    }

    private fun getMaxWidth(): Int {
        var maxWidth = 0
        for (i in 0 until childCount) {
            if (getChildAt(i).width > maxWidth)
                maxWidth = getChildAt(i).width
        }
        return maxWidth
    }

    private fun getTotalHeight(): Int {
        var totalHeight = 0
        for (i in 0 until childCount) {
            totalHeight += getChildAt(i).height
        }
        return totalHeight
    }
}