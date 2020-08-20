package com.example.coinz.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.coinz.R
import com.example.coinz.models.Currency
import kotlinx.android.synthetic.main.spinner_select_currency.view.*
import java.util.*

class SpinnerAdapter(val activity: Activity,var data:MutableList<Currency>): BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(activity).inflate(R.layout.spinner_select_currency,null,false)
        Glide.with(activity).load(data[p0].img).into(view.spinnerIcon)
        view.spinnerItemName.text = data[p0].name
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