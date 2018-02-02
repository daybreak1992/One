package com.tanghong.joker.ui.main

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import com.tanghong.commonlibrary.base.BaseActivity
import com.tanghong.commonlibrary.utils.StatusBarUtil
import com.tanghong.joker.R
import com.tanghong.joker.ui.message.MessageFragment
import com.tanghong.joker.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {

    private var homeFragment: HomeFragment? = null
    private var searchFragment: SearchFragment? = null
    private var messageFragment: MessageFragment? = null

    private var menuDate: MenuItem? = null
    private var menuSearch: MenuItem? = null
    private var exitTime: Long = 0

    override fun initPresenter(): MainPresenter = MainPresenter()

    override fun layoutId(): Int = R.layout.activity_main

    override fun initView() {
        presenter.attachView(this)
        StatusBarUtil.setTranslucentForDrawerLayout(this, drawer_main, 0)
        setSupportActionBar(toolBar)
        val toggle = ActionBarDrawerToggle(this, drawer_main, toolBar, R.string.drawer_open, R.string.drawer_close)
        toggle.syncState()
        drawer_main.addDrawerListener(toggle)

        toolBar.setNavigationOnClickListener { drawer_main.openDrawer(Gravity.LEFT) }
        bnv_navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            switchFragment(false, item.itemId)
            true
        })
    }

    override fun initData() {
        switchFragment(true, R.id.navigation_home)
    }

    override fun start() {

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
