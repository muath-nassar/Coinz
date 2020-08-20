package com.example.coinz.models

import com.google.gson.annotations.SerializedName

class CurrencyResponse(@SerializedName("currencies") var list: MutableList<Currency>) {
}