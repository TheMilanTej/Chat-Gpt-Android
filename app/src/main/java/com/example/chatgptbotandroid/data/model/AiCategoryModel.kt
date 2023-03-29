package com.example.chatgptbotandroid.data.model

import android.os.Parcel
import android.os.Parcelable

data class AiCategoryModel(
    val aiCategory: String, val aiDescription: String, val aiCategoryExample: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(aiCategory)
        parcel.writeString(aiDescription)
        parcel.writeStringList(aiCategoryExample)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AiCategoryModel> {
        override fun createFromParcel(parcel: Parcel): AiCategoryModel {
            return AiCategoryModel(parcel)
        }

        override fun newArray(size: Int): Array<AiCategoryModel?> {
            return arrayOfNulls(size)
        }
    }

}