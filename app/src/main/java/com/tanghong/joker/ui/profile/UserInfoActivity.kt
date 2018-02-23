package com.tanghong.joker.ui.profile

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.text.TextUtils
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.scwang.smartrefresh.layout.util.DensityUtil
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.joker.R
import com.tanghong.joker.glide
import com.tanghong.joker.showSnackbar
import http.ApiException
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_user_info.*
import model.Result
import model.User
import org.jetbrains.anko.backgroundColor

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class UserInfoActivity : BaseActivity<UserInfoPresenter>(), UserInfoContract.View {

    var userId: String = ""

    override fun initPresenter(): UserInfoPresenter = UserInfoPresenter()

    override fun layoutId(): Int = R.layout.activity_user_info

    override fun initView() {
        presenter.attachView(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        fab_add.setOnClickListener {
            it.showSnackbar(getString(R.string.btn_follow), Snackbar.LENGTH_SHORT)
        }
    }

    override fun initData() {
        userId = intent.getStringExtra("id")
    }

    override fun start() {
        showProgress()
        presenter.loadUser(userId)
    }

    override fun setUser(result: Result<User>) {
        closeProgress()
        toolbar.title = result.data.user_name
        tv_desc.text = result.data.desc
        iv_background.glide(result.data.background)
        val colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary)
        iv_avatar.glide(result.data.web_url,
                RequestOptions.bitmapTransform(RoundedCornersTransformation(DensityUtil.dp2px(35f), 0)),
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        val bitmapDrawable: BitmapDrawable = resource as BitmapDrawable
                        Palette.from(bitmapDrawable.bitmap).generate { palette: Palette? ->
                            if (TextUtils.isEmpty(result.data.background)) {
                                iv_background.backgroundColor = (palette?.getDarkVibrantColor(colorPrimary)
                                        ?: colorPrimary)
                            }
                        }
                        return false
                    }
                })
    }

    override fun setError(e: ApiException) {
        closeProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}