package com.example.coinz.models

import com.google.gson.annotations.SerializedName

data class TriggerListResponse(@SerializedName("condition") var list: MutableList<Alarm>)