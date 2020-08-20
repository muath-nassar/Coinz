package com.example.coinz.models

import com.google.gson.annotations.SerializedName

data class DeleteStatus(
  @SerializedName("status") val status: DeleteStatusResponse
)

data class DeleteStatusResponse(
  @SerializedName("success") val success: Boolean,
  @SerializedName("code") val code :Int
)