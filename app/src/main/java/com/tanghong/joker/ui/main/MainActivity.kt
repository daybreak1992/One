package com.tanghong.joker.ui.main

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.graphics.Palette
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.scwang.smartrefresh.layout.util.DensityUtil
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.commonlibrary.utils.JsonUtils
import com.tanghong.joker.R
import com.tanghong.joker.app.App
import com.tanghong.joker.glide
import com.tanghong.joker.openPage
import com.tanghong.joker.ui.message.MessageFragment
import com.tanghong.joker.ui.profile.LoginActivity
import com.tanghong.joker.ui.search.SearchFragment
import http.ApiException
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import model.User
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.toast


class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {
    private var homeFragment: HomeFragment? = null
    private var searchFragment: SearchFragment? = null
    private var messageFragment: MessageFragment? = null

    private var menuDate: MenuItem? = null
    private var menuSearch: MenuItem? = null
    private var exitTime: Long = 0

    private var rl_header_container: RelativeLayout? = null
    private var iv_avater: ImageView? = null
    private var tv_name: TextView? = null
    private var tv_desc: TextView? = null

    override fun initPresenter(): MainPresenter = MainPresenter()

    override fun layoutId(): Int = R.layout.activity_main

    override fun initView() {
        presenter.attachView(this)
        setSupportActionBar(toolBar)
        val toggle = ActionBarDrawerToggle(this, drawer_main, toolBar, R.string.drawer_open, R.string.drawer_close)
        toggle.syncState()
        drawer_main.addDrawerListener(toggle)
        initNavigationHeader()

        toolBar.setNavigationOnClickListener { drawer_main.openDrawer(Gravity.START) }
        bnv_navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            switchFragment(false, item.itemId)
            true
        })
        nv_navigation.setNavigationItemSelectedListener { item: MenuItem ->
            drawer_main.closeDrawer(Gravity.START)
            when (item.itemId) {
                R.id.menu_setting -> {
                    App.resetUser(null)
                    initNavigationHeader(App.user)
                }
            }
            true
        }
    }

    private fun initNavigationHeader() {
        if (nv_navigation.headerCount == 0) {
            return
        }
        val headerView = nv_navigation.getHeaderView(0)
        rl_header_container = headerView.findViewById(R.id.rl_header_container)
        iv_avater = headerView.findViewById(R.id.iv_avatar)
        tv_name = headerView.findViewById(R.id.tv_name)
        tv_desc = headerView.findViewById(R.id.tv_desc)

        iv_avater?.setOnClickListener {
            if (App.user == null) {
                openPage(LoginActivity::class.java)
            }
        }
    }

    override fun initData() {
        switchFragment(true, R.id.navigation_home)
    }

    override fun start() {

    }

    override fun onResume() {
        super.onResume()
        presenter.getUser()
    }

    /**
     * Palette:
     * Vibrant （有活力的）
     * Vibrant dark（有活力的 暗色）
     * Vibrant light（有活力的 亮色）
     * Muted （柔和的）
     * Muted dark（柔和的 暗色）
     * Muted light（柔和的 亮色）
     */
    override fun setUser(user: List<User>) {
        Log.i("main", "user = ${user[0].toString()}")
        App.resetUser(user[0])
        initNavigationHeader(App.user)
    }

    private fun initNavigationHeader(user: User?) {
        val color_white = ContextCompat.getColor(this, R.color.white)
        if (user == null) {
            rl_header_container?.backgroundColor = color_white
            iv_avater?.imageResource = R.drawable.msg_head
            tv_name?.text = ""
            tv_desc?.text = ""
        } else {
            iv_avater?.glide(user.web_url, RequestOptions.bitmapTransform(RoundedCornersTransformation(DensityUtil.dp2px(35f), 0)),
                    object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            val bitmapDrawable: BitmapDrawable = resource as BitmapDrawable
                            Palette.from(bitmapDrawable.bitmap).generate { palette: Palette? ->
                                rl_header_container?.backgroundColor = palette?.getMutedColor(color_white) ?: color_white
                            }
                            return false
                        }
                    })
            tv_name?.text = user.user_name
            tv_desc?.text = user.desc
        }
    }

    override fun setError(e: ApiException) {
        Log.i("main", "e = ${JsonUtils.serializeToJson(e)}")
    }

    private fun switchFragment(isInit: Boolean, itemId: Int) {
        if (!isInit && itemId == bnv_navigation.selectedItemId) {
            return
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        hideFragments(transaction)
        when (itemId) {
            R.id.navigation_home -> {
                toolBar.setTitle(R.string.title_home)
                menuDate?.isVisible = true
                if (homeFragment == null) {
                    homeFragment = HomeFragment.getInstance()
                    transaction.add(R.id.fl_content, homeFragment, "home")
                } else {
                    transaction.show(homeFragment)
                }
            }
            R.id.navigation_search -> {
                toolBar.setTitle(R.string.title_search)
                menuSearch?.isVisible = true
                if (searchFragment == null) {
                    searchFragment = SearchFragment.getInstance()
                    transaction.add(R.id.fl_content, searchFragment, "search")
                } else {
                    transaction.show(searchFragment)
                }
            }
            R.id.navigation_message -> {
                toolBar.setTitle(R.string.title_message)
                if (messageFragment == null) {
                    messageFragment = MessageFragment.getInstance()
                    transaction.add(R.id.fl_content, messageFragment, "message")
                } else {
                    transaction.show(messageFragment)
                }
            }
        }
        transaction.commitAllowingStateLoss()
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        menuDate?.isVisible = false
        menuSearch?.isVisible = false
        if (null != homeFragment) {
            transaction.hide(homeFragment)
        }
        if (null != searchFragment) {
            transaction.hide(searchFragment)
        }
        if (null != messageFragment) {
            transaction.hide(messageFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        menuDate = menu?.findItem(R.id.menu_date)
        menuSearch = menu?.findItem(R.id.menu_search)
        menuDate?.isVisible = true
        menuSearch?.isVisible = false
        when (bnv_navigation.selectedItemId) {
            R.id.navigation_home -> {
                menuDate?.isVisible = true
            }
            R.id.navigation_search -> {
                menuSearch?.isVisible = true
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.run {
            when (itemId) {
                R.id.menu_date -> {
                    createDateDialog()
                }
                R.id.menu_search -> {
                    searchFragment?.onSearchClick()
                }
                else -> {

                }
            }
        }
        return true
    }

    private fun createDateDialog() {
        val dateDialog = DatePickerDialog()
        dateDialog.show(supportFragmentManager, DatePickerDialog::class.java.simpleName)
        dateDialog.setOnDateCallback(object : DatePickerDialog.OnDateCallback {

            override fun onSelectedDate(date: String) {
                homeFragment?.selectDateRefresh(date)
                dateDialog.dismiss()
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.repeatCount == 0) {
            if (System.currentTimeMillis().minus(exitTime) <= 2000) {
                System.exit(0)
            } else {
                exitTime = System.currentTimeMillis()
                toast(getString(R.string.prompt_exit_app))
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
