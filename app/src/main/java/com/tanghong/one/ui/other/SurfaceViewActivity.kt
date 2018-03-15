package com.tanghong.one.ui.other

import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.SurfaceHolder
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.commonlibrary.utils.image.ImageUtils
import com.tanghong.one.R
import kotlinx.android.synthetic.main.activity_surface_view.*
import kotlinx.android.synthetic.main.activity_user_info.*

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/03/08
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * 程序为SurfaceHolder添加了一个CallBack实例，该Callback中定义了如下三个方法：
 * 1.void surfaceChanged(SurfaceHolder holder, int format, int width, int height):当一个surface的格式或大小发生改变时回调该方法。
 * 2.void surfaceCreated(SurfaceHolder holder):当surface被创建时回调该方法
 * 3.void surfaceDestroyed(SurfaceHolder holder):当surface将要被销毁时回调该方法
 */
class SurfaceViewActivity : BaseActivity<SurfaceViewPresenter>(), SurfaceViewContract.View {
    var holder: SurfaceHolder? = null
    var paint: Paint? = null

    override fun initPresenter(): SurfaceViewPresenter = SurfaceViewPresenter()

    override fun layoutId(): Int = R.layout.activity_surface_view

    override fun initView() {
        presenter.attachView(this)
        toolbar.title = getString(R.string.title_surface_view)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        paint = Paint()
        holder = sv.holder
        holder?.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {

            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
                val canvas = holder?.lockCanvas()
                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.opening_monday)
                canvas?.drawBitmap(bitmap, 0f, 0f, null)
                // 绘制完成，释放画布，提交修改
                holder?.unlockCanvasAndPost(canvas)
                // 重新锁一次，"持久化"上次所绘制的内容
                holder?.lockCanvas(Rect(0, 0, 0, 0))
                holder?.unlockCanvasAndPost(canvas)
            }
        })
        sv.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val downX = event.x
                    val downY = event.y
                    // 锁定SurfaceView的局部区域，只更新局部内容
                    val canvas = holder?.lockCanvas(Rect((downX - 50).toInt(), (downY - 50).toInt(),
                            (downX + 50).toInt(), (downY + 50).toInt()))
                    // 保存canvas的当前状态
                    canvas?.save()
                    // 旋转画布
                    canvas?.rotate(30f, downX, downY)
                    paint?.color = resources.getColor(R.color.colorAccent)
                    canvas?.drawRect(downX - 40, downY - 40, downX, downY, paint)
                    // 恢复Canvas之前的保存状态
                    canvas?.restore()
                    paint?.color = resources.getColor(R.color.colorPrimary)
                    canvas?.drawRect(downX, downY, downX + 40, downY + 40, paint)
                    // 绘制完成，释放画布，提交修改
                    holder?.unlockCanvasAndPost(canvas)
                }
            }
            false
        }

        btn_load_image.setOnClickListener {
            val url = "http://pic3.maimengjun.com/e25bda8dcb8570376467cb6b3a1bee65.jpg?imageView2/2/w/600/h/900"
            ImageUtils().display(iv_load, url)
        }
    }

    override fun initData() {
        val url = "http://pic3.maimengjun.com/e25bda8dcb8570376467cb6b3a1bee65.jpg?imageView2/2/w/600/h/900"
        ImageUtils().display(iv, url)
    }

    override fun start() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}