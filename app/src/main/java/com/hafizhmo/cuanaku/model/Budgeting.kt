package com.hafizhmo.cuanaku.model

data class Budgeting(
    val error: Boolean,
    val message: String,
    val budget: Budget
){
    data class Budget(
        val id: Int,
        val remaining_budget: Int,
        val total_expenses: Int,
        val total_budget: Int,
        val created_at: String,
    )
}
