package com.hafizhmo.cuanaku.model

data class Transactions(
    val error: Boolean,
    val message: String,
    val transactions: List<Transactions>
){
    data class Transactions(
        val id: Int,
        val amount: Int,
        val created_at: String,
        val category: Category.Categories
    )
}
