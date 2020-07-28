package com.example.coinz.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.coinz.R
import com.example.coinz.models.Currency
import kotlinx.android.synthetic.main.text_spinner.view.*
import java.util.*

class SpinnerAlarmTypeAdapter(val activity: Activity,var data:Array<String>): BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(activity).inflate(R.layout.text_spinner,null,false)
        view.tvType.text = data[p0]
        return view
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return  data.size
    }
}