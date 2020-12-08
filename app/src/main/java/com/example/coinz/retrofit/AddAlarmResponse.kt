package com.example.coinz.retrofit

import com.google.gson.annotations.SerializedName

data class AddAlarmResponse(
    @SerializedName("status") val status: AlarmAddStatus
)

data class AlarmAddStatus(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message :String
)