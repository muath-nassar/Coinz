package com.example.coinz.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinz.R
import com.example.coinz.adapters.CurrencyTableAdapter
import com.example.coinz.adapters.SelectedCurrencyRvAdapter
import com.example.coinz.models.Currency
import kotlinx.android.synthetic.main.fragment_price.*
import kotlinx.android.synthetic.main.fragment_price.view.*


class PriceFragment : Fragment() {
    lateinit var root:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_price, container, false)
        return root
    }

    override fun onStart() {
        super.onStart()
        val data = mutableListOf<Currency>()
        data.add(Currency(null,null,null)); data.add(Currency(null,null,null));
        data.add(Currency(null,null,null)); data.add(Currency(null,null,null));

        val gridLayoutManager = GridLayoutManager(activity,2)
        val adapter = SelectedCurrencyRvAdapter(activity!!.applicationContext,data)
        rvSelectedCurrencies.adapter = adapter
        rvSelectedCurrencies.layoutManager = gridLayoutManager
        // define the second adapter with the it RV
        val linearLayoutManager = LinearLayoutManager(activity)
        val adapter2 = CurrencyTableAdapter(activity!!.applicationContext,data)
        rvCurrencyList.adapter =adapter2
        rvCurrencyList.layoutManager= linearLayoutManager
    }

    override fun onResume() {
        super.onResume()



    }


}