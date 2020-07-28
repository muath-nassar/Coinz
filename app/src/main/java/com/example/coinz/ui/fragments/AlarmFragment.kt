package com.example.coinz.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinz.R
import com.example.coinz.adapters.AlarmAdapter_RV
import com.example.coinz.adapters.SpinnerAdapter
import com.example.coinz.adapters.SpinnerAlarmTypeAdapter
import com.example.coinz.models.Alarm
import com.example.coinz.models.Currency
import kotlinx.android.synthetic.main.fragment_alarm.*
import kotlinx.android.synthetic.main.fragment_alarm.view.*

class AlarmFragment : Fragment() {
    companion object {
        val alarmTypes = arrayOf("أكبر من", "أصغر من", "يساوي")
        lateinit var spinnerAdapter: SpinnerAlarmTypeAdapter
    }

    private lateinit var root: View
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
        spinnerAdapter = SpinnerAlarmTypeAdapter(activity!!, alarmTypes)
    }

    override fun onStart() {
        super.onStart()
        //dummy data
        val data = mutableListOf<Currency>()
        data.add(Currency(null, null, null)); data.add(Currency(null, null, null));
        data.add(Currency(null, null, null)); data.add(Currency(null, null, null));
        root.spinnerCurrency.adapter = SpinnerAdapter(activity!!, data)
        //----------
        root.spinnerAlarmType.adapter = spinnerAdapter
        val alarms = mutableListOf<Alarm>()
        val alarm = Alarm(1,"sd",52.0)
        alarms.add(alarm);
        alarms.add(alarm);
        alarms.add(alarm);
        alarms.add(alarm);
        alarms.add(alarm);
        alarms.add(alarm);

        rvAlarms.adapter = AlarmAdapter_RV(activity!!,alarms)
        rvAlarms.layoutManager= LinearLayoutManager(this.activity)
    }

}