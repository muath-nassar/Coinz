package com.example.coinz.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coinz.R
import com.example.coinz.adapters.SpinnerAdapter
import com.example.coinz.models.Currency
import kotlinx.android.synthetic.main.fragment_alarm.view.*

class AlarmFragment : Fragment() {
private lateinit var root:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_alarm, container, false)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
 val s = "هل هو"
        s
    }

    override fun onStart() {
        super.onStart()
        //dummy data
        val data = mutableListOf<Currency>()
        data.add(Currency(null,null,null)); data.add(Currency(null,null,null));
        data.add(Currency(null,null,null)); data.add(Currency(null,null,null));
        root.spinnerCurrency.adapter = SpinnerAdapter(activity!!,data)
    }

}