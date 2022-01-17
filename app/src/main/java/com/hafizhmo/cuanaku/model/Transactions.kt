package com.hafizhmo.cuanaku.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Transactions(
    val error: Boolean,
    val message: String,
    val transactions: List<Transactions>
) {
    @Parcelize
    data class Transactions(
        val id: Int,
        val amount: Int,
        val created_at: String,
        val category: Category.Categories
    ) : Parcelable
}
