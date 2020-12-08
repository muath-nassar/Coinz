package com.example.coinz.ui.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinz.R
import com.example.coinz.adapters.CurrencyTableAdapter
import com.example.coinz.adapters.SelectedCurrencyRvAdapter
import com.example.coinz.models.Currency
import com.example.coinz.models.CurrencyResponse
import com.example.coinz.retrofit.ApiClient
import com.example.coinz.retrofit.ApiInterface
import com.example.coinz.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_price.*
import kotlinx.android.synthetic.main.fragment_price.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.text.DateFormat
import java.util.*


class PriceFragment : Fragment() {
    lateinit var currencyList: MutableList<Currency>
    lateinit var root: View
    lateinit var currencyTableAdapter: CurrencyTableAdapter
    val data = mutableListOf<Currency>()


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
        getCurrencies()
        var isDone = false
        var itemHeight = 0
        val thread = Thread {
            while (!isDone) {
                try {
                    if (rvCurrencyList.findViewHolderForLayoutPosition(0) != null) {
                        itemHeight =
                            rvCurrencyList.findViewHolderForLayoutPosition(0)!!.itemView.layoutParams.height
                        rvCurrencyList.layoutParams.height =
                            currencyTableAdapter.data.size * (itemHeight)
                        isDone = true
                    }
                }catch (e: Exception){e.printStackTrace()}

                Thread.sleep(100)
            }
        }
        thread.start()


    }
    private fun getCurrenciesToSelectedAdapter(){
        root.progress_circular_select.visibility = View.VISIBLE
        val cList = (activity as MainActivity).currencyList
        Log.e("mmm","0000000"+cList.toString())
        val pref = (activity as MainActivity).sharedPreferences
        for (i in 0 ..  3){
            val code = pref.getString("c_code_"+i,null)
            Log.e("mmm","code = "+code)
            if (code != null){
                //111
             for (c in cList){
                 if (c.code == code && data.size<=3){
                     data.add(c)
                 }
             }

                //2222
            }else{
                if (data.size<=3) data.add(Currency(null, null, null, null,null))
            }
        }
        updateSelectedAdapter()
        root.progress_circular_select.visibility = View.GONE
    }
    private  fun finishLoading(){
        root.progress_circular_table.visibility = View.GONE
    }

    private fun getCurrencies() {
        root.progress_circular_table.visibility = View.VISIBLE
        val service = ApiClient.getInstance().create(ApiInterface::class.java)
        val call = service.getCurrencies()
        call.enqueue(object : Callback<CurrencyResponse> {
            override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                Log.e("mmm", "price fragment error \n"+t.localizedMessage)
                Toast.makeText(activity,"هماك خطأ في عملية جلب البيانات.", Toast.LENGTH_SHORT).show()
                finishLoading()
            }
            override fun onResponse(
                call: Call<CurrencyResponse>,
                response: Response<CurrencyResponse>
            ) {
                val data = response.body()
                setAdapter(data!!.list)
                currencyList = data.list
                (activity as MainActivity).currencyList.clear()
                (activity as MainActivity).currencyList.addAll(currencyList)
                getCurrenciesToSelectedAdapter()
                root.tvLastUpdate.text = getLastUpdateText()
                finishLoading()
            }
        })
    }

    private fun setAdapter(list: MutableList<Currency>) {
        // define the second adapter with the it RV
        currencyTableAdapter = CurrencyTableAdapter(activity!!.applicationContext, list)
        root.rvCurrencyList.adapter = currencyTableAdapter
        root.rvCurrencyList.isNestedScrollingEnabled = false
        root.rvCurrencyList.layoutManager = object : LinearLayoutManager(activity) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }

            override fun canScrollVertically(): Boolean {
                return false
            }
        }

    }

    private fun getLastUpdateText(): String {
        val currentDateTimeString =
            DateFormat.getDateTimeInstance().format(Date())
        return "آخر تحديث  " + currentDateTimeString

    }
      fun updateSelectedAdapter(){
         Log.e("mmm",data.toString())
         val gridLayoutManager = object : GridLayoutManager(activity, 2) {
             override fun canScrollHorizontally(): Boolean {
                 return false
             }
             override fun canScrollVertically(): Boolean {
                 return false
             }
         }
         val adapter = SelectedCurrencyRvAdapter(activity!!, data,this)
         rvSelectedCurrencies.adapter = adapter
         rvSelectedCurrencies.layoutManager = gridLayoutManager
    }
}