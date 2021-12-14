package com.hafizhmo.cuanaku.model

data class Expenses(
    val timestamp: String,
    val nominal: String,
    val category: Category
){
    data class Category(
        val name: String,
        val color: Int,
        val image: Int
    )
}
