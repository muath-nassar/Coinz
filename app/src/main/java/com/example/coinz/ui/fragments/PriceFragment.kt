package com.example.coinz.ui.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import java.text.DateFormat
import java.util.*


class PriceFragment : Fragment() {
    lateinit var currencyList: MutableList<Currency>
    lateinit var root: View
    lateinit var currencyTableAdapter: CurrencyTableAdapter
    lateinit var progressDialog: ProgressDialog

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
        val data = mutableListOf<Currency>()
        data.add(Currency(null, null, null, null,null));
        data.add(Currency(null, null, null, null,null));
        data.add(Currency(null, null, null, null,null));
        data.add(Currency(null, null, null, null,null));


        val gridLayoutManager = object : GridLayoutManager(activity, 2) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }

            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        val adapter = SelectedCurrencyRvAdapter(activity!!, data)
        rvSelectedCurrencies.adapter = adapter
        rvSelectedCurrencies.layoutManager = gridLayoutManager

    }

    override fun onResume() {
        super.onResume()
        var isDone = false
        var itemHeight = 0
        val thread = Thread {
            while (isDone == false) {
                if (rvCurrencyList.findViewHolderForLayoutPosition(0) != null) {
                    itemHeight =
                        rvCurrencyList.findViewHolderForLayoutPosition(0)!!.itemView.layoutParams.height
                    rvCurrencyList.layoutParams.height =
                        currencyTableAdapter.data.size * (itemHeight)
                    isDone = true
                }
                Thread.sleep(100)
            }
        }
        thread.start()
    }

    private fun getCurrencies() {

        // progressDialog.show()
        val service = ApiClient.getInstance().create(ApiInterface::class.java)
        val call = service.getCurrencies()
        call.enqueue(object : Callback<CurrencyResponse> {
            override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                Log.e("mmm", "price fragment error \n"+t.localizedMessage)

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

                root.tvLastUpdate.text = getLastUpdateText()
            }
        })
    }

    private fun setAdapter(list: MutableList<Currency>) {
        // define the second adapter with the it RV
        val linearLayoutManager = LinearLayoutManager(activity)
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
}