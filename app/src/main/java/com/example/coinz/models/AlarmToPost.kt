package com.example.coinz.models

import com.google.gson.annotations.SerializedName

data class AlarmToPost(
    @SerializedName("i_type") val type: Int,
    @SerializedName("d_value") val amount: Double,
    @SerializedName("s_code") val code: String,
    @SerializedName("s_udid") val uduid: String = "testmmm"
)