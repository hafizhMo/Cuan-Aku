package com.hafizhmo.cuanaku.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Budgetingss(
    val error: Boolean,
    val message: String,
    val budget: List<Budget>
){
    @Parcelize
    data class Budget(
        val id: Int,
        val remaining_budget: Int,
        val total_expenses: Int,
        val total_budget: Int,
        val month: String,
        val year: String,
        val created_at: String,
    ): Parcelable
}
