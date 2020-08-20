package com.example.coinz.retrofit

import com.example.coinz.ApplicationHelper
import com.example.coinz.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("currencies/list")
    fun getCurrencies(): Call<CurrencyResponse>

    @Headers("X-Client-Device-UDID: testmmm")
    @GET("triggers/list")
    fun getTriggers():Call<TriggerListResponse>

    @POST("triggers")
    fun addTrigger(@Body alarm: AlarmToPost):Call<Alarm>

    @DELETE("triggers")
    fun deleteTrigger(@Query("id") alarmId : Int):Call<DeleteStatus>
}