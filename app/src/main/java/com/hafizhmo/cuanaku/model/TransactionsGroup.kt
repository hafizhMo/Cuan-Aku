package com.hafizhmo.cuanaku.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class TransactionsGroup(
    val error: Boolean,
    val message: String,
    val transactions: List<Transactions>
) {
    @Parcelize
    data class Transactions(
        val sum: Int,
        val category: Category.Categories
    ) : Parcelable
}
