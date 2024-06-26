package com.example.coinz.models

import com.google.gson.annotations.SerializedName

data class Alarm(
    @SerializedName("i_type") val type: Int,
    @SerializedName("s_name") val currencyName: String,
    @SerializedName("d_value") val amount: Double,
    @SerializedName("s_icon") val icon: String?,
    @SerializedName("s_code") val code: String,
    @SerializedName("pk_i_id") val id: Int
)