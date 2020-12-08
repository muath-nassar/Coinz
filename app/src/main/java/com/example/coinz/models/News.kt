package com.example.coinz.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("s_title") val title: String?,
    @SerializedName("s_image") val img: String?,
    @SerializedName("s_description") val description: String?,
    @SerializedName("dt_created_date") val date: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(img)
        parcel.writeString(description)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}