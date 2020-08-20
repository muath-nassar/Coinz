package com.example.coinz.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinz.R
import com.example.coinz.adapters.AlarmAdapter_RV
import com.example.coinz.adapters.SpinnerAdapter
import com.example.coinz.adapters.SpinnerAlarmTypeAdapter
import com.example.coinz.models.Alarm
import com.example.coinz.models.AlarmToPost
import com.example.coinz.models.OnDeleteHandler
import com.example.coinz.models.TriggerListResponse
import com.example.coinz.retrofit.ApiClient
import com.example.coinz.retrofit.ApiInterface
import com.example.coinz.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_alarm.*
import kotlinx.android.synthetic.main.fragment_alarm.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlarmFragment : Fragment(),OnDeleteHandler {
    private lateinit var triggerList: MutableList<Alarm>
    companion object {
        val headerUDID = "testmmm"// this should be something else in the actual application
        val alarmTypes = arrayOf("أصغر من", "يساوي","أكبر من")
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
        getTriggersList()
    }

    private fun getTriggersList() {
        val service = ApiClient.getInstance().create(ApiInterface::class.java)
        val call = service.getTriggers()
        call.enqueue(object : Callback<TriggerListResponse>{
            override fun onFailure(call: Call<TriggerListResponse>, t: Throwable) {
                Log.e("mmm", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<TriggerListResponse>,
                response: Response<TriggerListResponse>
            ) {
                Log.e("mmm",response.raw().toString())
                triggerList = response.body()!!.list
                updateAdapter(triggerList)
            }

        })
    }

    override fun onStart() {
        super.onStart()
        val data = (activity as MainActivity).currencyList
        root.spinnerCurrency.adapter = SpinnerAdapter(activity!!, data)
        //----------

        root.spinnerAlarmType.adapter = spinnerAdapter
    }
    private fun updateAdapter(list : MutableList<Alarm>){
        root.rvAlarms.adapter = AlarmAdapter_RV(activity!!,list,this)
        root.rvAlarms.layoutManager= LinearLayoutManager(this.activity)
        Log.e("mmm",list.size.toString())
    }
    private fun handleBtnNewTrigger(){
        val isReadyToSubmit = etMoneyAmountAlarm.text.isNotEmpty()
        if (isReadyToSubmit){
            addTrigger()

        }

    }

    private fun addTrigger() {

        val service =ApiClient.getInstance().create(ApiInterface::class.java)
        // get alarm constructor atteributes
        val type = spinnerAlarmType.selectedItemPosition+1
        val value = etMoneyAmountAlarm.text.toString()
        val currencyPosition = spinnerCurrency.selectedItemPosition
        val currency = (activity as MainActivity).currencyList[currencyPosition]
        val sCode = currency.code
        val postAlarm = AlarmToPost(type,value.toDouble(),sCode!!)

        val call = service.addTrigger(postAlarm)
        call.enqueue(object: Callback<Alarm>{
            override fun onFailure(call: Call<Alarm>, t: Throwable) {
                t.printStackTrace()
                Log.e("mmm","this error is from the post "+t.localizedMessage)

            }

            override fun onResponse(call: Call<Alarm>, response: Response<Alarm>) {
                getTriggersList()
            }

        })

    }

    override fun onResume() {
        super.onResume()
        btnNewTrigger.setOnClickListener {
            handleBtnNewTrigger()
        }
    }

    override fun onDeleteSuccess() {
        getTriggersList()
    }


}