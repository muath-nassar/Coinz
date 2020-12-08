package com.example.coinz.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinz.R
import com.example.coinz.adapters.AlarmAdapter_RV
import com.example.coinz.adapters.SpinnerAdapter
import com.example.coinz.adapters.SpinnerAlarmTypeAdapter
import com.example.coinz.models.Alarm
import com.example.coinz.models.AlarmToPost
import com.example.coinz.models.OnDeleteHandler
import com.example.coinz.models.TriggerListResponse
import com.example.coinz.retrofit.AddAlarmResponse
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
        val alarmTypes = arrayOf("أصغر من", "يساوي", "أكبر من")
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

    private fun getTriggersList() {
        root.progress_circular_table.visibility = View.VISIBLE
        val service = ApiClient.getInstance().create(ApiInterface::class.java)
        val call = service.getTriggers()
        call.enqueue(object : Callback<TriggerListResponse> {
            override fun onFailure(call: Call<TriggerListResponse>, t: Throwable) {
                Log.e("mmm", t.localizedMessage!!)
                root.progress_circular_table.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<TriggerListResponse>,
                response: Response<TriggerListResponse>
            ) {
                Log.e("mmm", response.raw().toString())
                triggerList = response.body()!!.list
                updateAdapter(triggerList)
                root.progress_circular_table.visibility = View.GONE
            }

        })
    }

    override fun onStart() {
        super.onStart()
        spinnerAdapter = SpinnerAlarmTypeAdapter(activity!!, alarmTypes)
        getTriggersList()
        val data = (activity as MainActivity).currencyList
        root.spinnerCurrency.adapter = SpinnerAdapter(activity!!, data)
        //----------

        root.spinnerAlarmType.adapter = spinnerAdapter
    }
    private fun updateAdapter(list: MutableList<Alarm>){
        root.rvAlarms.adapter = AlarmAdapter_RV(activity!!, list, this)
        root.rvAlarms.layoutManager= LinearLayoutManager(this.activity)
        Log.e("mmm", list.size.toString())
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
        val postAlarm = AlarmToPost(type, value.toDouble(), sCode!!)

        val call = service.addTrigger(postAlarm)
        call.enqueue(object : Callback<AddAlarmResponse> {
            override fun onFailure(call: Call<AddAlarmResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("mmm", "هناك خطأ بالاتصال. يرجى المعاودة لاحقا. " + t.localizedMessage)

            }

            override fun onResponse(call: Call<AddAlarmResponse>, response: Response<AddAlarmResponse>) {
                getTriggersList()
                Log.e("addd",response.toString())
                if (response.code() == 200)
                Toast.makeText(context,response.body()!!.status.message,Toast.LENGTH_SHORT).show()
                else{
                    Toast.makeText(context,"التنبيه مضاف مسبقا",Toast.LENGTH_SHORT).show()
                }
            }

        })

    }

    override fun onResume() {
        super.onResume()
        btnNewTrigger.setOnClickListener {
            handleBtnNewTrigger()
            hideKeyboard(activity!!)

        }
    }

    override fun onDeleteSuccess() {
        getTriggersList()
    }
    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}