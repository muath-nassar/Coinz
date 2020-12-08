package com.example.coinz.retrofit

import com.example.coinz.models.News
import com.google.gson.annotations.SerializedName

data class NewsListResponse(@SerializedName("news") val list : MutableList<News>)
