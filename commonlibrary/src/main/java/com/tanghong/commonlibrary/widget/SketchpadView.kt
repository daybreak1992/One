package com.tanghong.commonlibrary.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class SketchpadView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
        SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {
    private val TAG = "SketchpadView"

    private var mHolder: SurfaceHolder? = null
    private var mCanvas: Canvas? = null
    private var mPaint: Paint? = null
    private var mPath: Path? = null
    private var mIsDrawing = false

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        mPaint = Paint().apply {
            color = Color.GRAY
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
            isDither = true
            strokeWidth = 10f
        }
        mPath = Path()
        mHolder = holder;
        mHolder?.addCallback(this)
        isFocusable = true
        isFocusableInTouchMode = true
        keepScreenOn = true

    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        mIsDrawing = false
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        mIsDrawing = true
        Thread(DrawRunnable()).start()
    }

    inner class DrawRunnable : Runnable {

        override fun run() {
            if (mIsDrawing) {
                draw()
            }
        }
    }

    private fun draw() {
        try {
            mCanvas = mHolder?.lockCanvas()
            mCanvas?.drawColor(Color.WHITE)
            mCanvas?.drawPath(mPath, mPaint)
        } catch (e: Exception) {
            Log.i(TAG, "e = ${e.toString()}")
        } finally {
            if (mCanvas != null) {
                mHolder?.unlockCanvasAndPost(mCanvas)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mPath?.moveTo(x!!, y!!)
            }
            MotionEvent.ACTION_MOVE -> {
                mPath?.lineTo(x!!, y!!)
            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return true
    }
}