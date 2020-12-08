package com.example.coinz.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar
import androidx.viewpager.widget.ViewPager
import com.example.coinz.R
import com.example.coinz.adapters.MyPagerAdapter
import com.example.coinz.models.Currency
import com.example.coinz.ui.fragments.AlarmFragment
import com.example.coinz.ui.fragments.NewsFragment
import com.example.coinz.ui.fragments.PriceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_price.view.*

class MainActivity : AppCompatActivity() {
    companion object {
    }

    lateinit var priceFragment: PriceFragment
    lateinit var alarmFragment: AlarmFragment
    lateinit var newsFragment: NewsFragment
    lateinit var sharedPreferences: SharedPreferences
    var currencyList: MutableList<Currency> = mutableListOf()
    private lateinit var loadingProgressBar: ContentLoadingProgressBar

    // ----------------------
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            newsDescriptionFragHolder.visibility = View.GONE
            mainFrame.visibility = View.VISIBLE
            makeNavIconsGray()
            when (item.itemId) {

                R.id.navPrice -> {
                    // replaceFragment(priceFragment)
                    mainFrame.currentItem = 0
                    item.setIcon(R.drawable.ic_bottom_price)
                }
                R.id.navAlarm -> {
                    // replaceFragment(alarmFragment)
                    mainFrame.currentItem = 1
                    item.setIcon(R.drawable.ic_bottom_alarm)
                }
                R.id.navNews -> {
                    // replaceFragment(newsFragment)
                    mainFrame.currentItem = 2
                    item.setIcon(R.drawable.ic_bottom_news)
                }
            }
            return@OnNavigationItemSelectedListener true
        }
    // ------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingBarInstantiate()
        instantiateFrags()
        sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE)

        val list = intent.getParcelableArrayListExtra<Parcelable>("c_list")
        if (list != null) {
            for (i in list) {
                currencyList.add(i as Currency)
            }
        }

        // create pager adapter
        val pAd = MyPagerAdapter(this, supportFragmentManager)
        mainFrame.adapter = pAd
        // pager Listener
        mainFrame.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                // ****
                makeNavIconsGray()

                when (position) {
                    0 -> {
                        navBottom.selectedItemId = R.id.navPrice
                    }

                    1 -> {
                        navBottom.selectedItemId = R.id.navAlarm
                    }
                    2 -> {
                        navBottom.selectedItemId = R.id.navNews
                    }
                }
                // ***
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    override fun onStart() {
        super.onStart()
        onOpenFirstTimeMenu()
    }
    private fun onOpenFirstTimeMenu() {
        navBottom.itemIconTintList = null
        navBottom.selectedItemId = R.id.navPrice
        navBottom.selectedItemId = R.id.navPrice
        navBottom.selectedItemId = R.id.navPrice
        navBottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun loadingBarInstantiate() {
        loadingProgressBar = ContentLoadingProgressBar(this)
    }

    private fun instantiateFrags() {
        priceFragment = PriceFragment()
        alarmFragment = AlarmFragment()
        newsFragment = NewsFragment()
    }

    fun makeNavIconsGray() {
        navBottom.menu.findItem(R.id.navPrice).setIcon(R.drawable.ic_price_gray)
        navBottom.menu.findItem(R.id.navAlarm).setIcon(R.drawable.ic_bell_gray)
        navBottom.menu.findItem(R.id.navNews).setIcon(R.drawable.ic_menu_gray)
    }
}
