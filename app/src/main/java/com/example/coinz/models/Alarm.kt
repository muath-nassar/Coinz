package com.example.coinz.models

data class Alarm(val type: Int ,val currencyName: String,val  amount: Double){
    companion object{
        final val MORE = 1
        final val LESS = 2
        final val EQUAL = 3
    }
}
