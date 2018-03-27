package com.tanghong.commonlibrary.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.tanghong.commonlibrary.R

/**
 * Created by tanghong on 2018/3/27.
 */
class CustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val TAG = "CustomView"

    private var paint: Paint? = null
    private var defaultSize = 100

    constructor(context: Context?) : this(context, null)

    init {
        paint = Paint().apply {
            color = Color.RED
        }

        var ta: TypedArray? = null
        try {
            ta = context?.obtainStyledAttributes(attrs, R.styleable.CustomView)
            defaultSize = ta?.getDimensionPixelSize(R.styleable.CustomView_default_size, 100)!!
        } finally {
            ta?.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = getMeasureSize(defaultSize, widthMeasureSpec)
        var height = getMeasureSize(defaultSize, heightMeasureSpec)

        if (width < height) {
            height = width
        } else {
            width = height
        }

        Log.i(TAG, "width = $width; height = $height")
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        //调用父View的onDraw函数，因为View这个类帮我们实现了一些
        //基本的而绘制功能，比如绘制背景颜色、背景图片等
        super.onDraw(canvas)
        val r = measuredWidth / 4

        val cx = measuredWidth / 2
        val cy = measuredHeight / 2

        Log.i(TAG, "r = $r; cx = $cx; cy = $cy")
        canvas?.drawCircle(cx.toFloat(), cy.toFloat(), r.toFloat(), paint)
        paint?.color = Color.GREEN

        val px = left + measuredWidth / 2
        val py = top + measuredHeight / 2
        canvas?.drawText("hello world !", px.toFloat(), py.toFloat(), paint)
    }

    private fun getMeasureSize(defaultSize: Int, measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)

        var measureSize = defaultSize
        when (mode) {
        //如果测量模式是最大取值为size
        //我们将大小取最大值,你也可以取其他值
            MeasureSpec.AT_MOST -> {
                measureSize = size
            }
        //如果是固定的大小，那就不要去改变它
            MeasureSpec.EXACTLY -> {
                measureSize = size
            }
        //如果没有指定大小，就设置为默认大小
            MeasureSpec.UNSPECIFIED -> {
                measureSize = defaultSize
            }
        }

        return measureSize
    }
}