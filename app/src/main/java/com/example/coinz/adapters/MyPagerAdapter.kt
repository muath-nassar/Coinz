package com.example.coinz.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.coinz.ui.MainActivity
import com.example.coinz.ui.fragments.AlarmFragment
import kotlinx.android.synthetic.main.activity_main.*

class MyPagerAdapter(val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object{
        private val NUM_ITEMS_=3
    }
    override fun getCount(): Int {
        return NUM_ITEMS_
    }

    override fun getItem(position: Int): Fragment {
        val activity = (context as MainActivity)
        when(position){
            0 -> {

             //   activity.navBottom.menu.getItem(0).setIcon(R.drawable.ic_bottom_price)
                return activity.priceFragment
            }
            1 -> {
               // activity.navBottom.menu.getItem(1).setIcon(R.drawable.ic_bottom_alarm)
                return AlarmFragment()
            }
            2 -> {
                //activity.navBottom.menu.getItem(2).setIcon(R.drawable.ic_bottom_news)
                return activity.newsFragment
            }
        }
        return activity.priceFragment
    }
}