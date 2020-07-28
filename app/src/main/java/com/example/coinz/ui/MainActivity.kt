package com.example.coinz.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coinz.R
import com.example.coinz.ui.fragments.AlarmFragment
import com.example.coinz.ui.fragments.NewsFragment
import com.example.coinz.ui.fragments.PriceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //----------------------
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            makeNavIconsGray()
            when (item.itemId) {
                R.id.navPrice -> {
                    replaceFragment(PriceFragment())
                    item.setIcon(R.drawable.ic_bottom_price)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navAlarm -> {
                    replaceFragment(AlarmFragment())
                    item.setIcon(R.drawable.ic_bottom_alarm)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navNews -> {
                    replaceFragment(NewsFragment())
                    item.setIcon(R.drawable.ic_bottom_news)

                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
    //------------------


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navBottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navBottom.selectedItemId = R.id.navPrice
        navBottom.itemIconTintList = null
        //----------
/*        val states = arrayOf(
            intArrayOf(-android.R.attr.state_active),
            intArrayOf(android.R.attr.state_pressed)
        )

        val colors = intArrayOf(
            Color.RED,
            Color.GREEN
        )

        val myList = ColorStateList(states, colors)*/
        //----------

/*
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = Color.WHITE

*/

    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame, fragment).commit()
    }

    private fun makeNavIconsGray() {
        navBottom.menu.findItem(R.id.navPrice).setIcon(R.drawable.ic_price_gray)
        navBottom.menu.findItem(R.id.navAlarm).setIcon(R.drawable.ic_bell_gray)
        navBottom.menu.findItem(R.id.navNews).setIcon(R.drawable.ic_menu_gray)
    }
}