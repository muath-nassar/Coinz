package com.example.coinz

import android.app.Application
import android.provider.Settings
import android.util.Log
import java.util.*

class ApplicationHelper: Application() {
    companion object{
        fun getDeviceId(): String{
            return deviceId
        }
        private var deviceId : String = ""// need to rewrite in future
        private var singleton: ApplicationHelper? = null
        fun getInstance(): ApplicationHelper{
            if (singleton==null) {
                singleton = ApplicationHelper()
            }
            return singleton!!
        }
    }





    override fun onCreate() {
        super.onCreate()
        Log.e("mmm", deviceId)
    }
}