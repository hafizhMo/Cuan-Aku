package com.hafizhmo.cuanaku.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Category(
    val error: Boolean,
    val message: String,
    val categories: List<Categories>
){
    @Parcelize
    data class Categories(
        val id: Int,
        val slug: String,
        val image: String
    ): Parcelable
}
