package com.example.coinz.models

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("s_name") val name: String?,
    @SerializedName("s_icon")  val img: String?,
    @SerializedName("d_trading")  var trading: Double?,
    @SerializedName("d_value")  var value: Double?,
    @SerializedName("s_code") val code: String?
)
