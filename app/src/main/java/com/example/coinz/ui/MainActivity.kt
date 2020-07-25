package com.example.coinz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            when (item.itemId) {
                R.id.navPrice -> {
                    replaceFragment(PriceFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navAlarm -> {
                    replaceFragment(AlarmFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navNews -> {
                    replaceFragment(NewsFragment())
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

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame, fragment).commit()
    }
}