package com.example.coinz

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.coinz.models.CurrencyResponse
import com.example.coinz.retrofit.ApiClient
import com.example.coinz.retrofit.ApiInterface
import com.example.coinz.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        getCurrencies()
    }

    override fun onResume() {
        super.onResume()
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        actionBar?.hide()
    }

    private fun getCurrencies() {

        // progressDialog.show()
        val service = ApiClient.getInstance().create(ApiInterface::class.java)
        val call = service.getCurrencies()
        call.enqueue(object : Callback<CurrencyResponse> {
            override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                Log.e("mmm", "price fragment error \n" + t.localizedMessage)
            }
            override fun onResponse(
                call: Call<CurrencyResponse>,
                response: Response<CurrencyResponse>
            ) {
                val data = response.body()!!.list
                val arrayList = java.util.ArrayList<Parcelable>()
                for (i in data) {
                    arrayList.add(i as Parcelable)
                }
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                intent.putParcelableArrayListExtra("c_list", arrayList)
                startActivity(intent)
                finish()
            }
        })
    }
}
