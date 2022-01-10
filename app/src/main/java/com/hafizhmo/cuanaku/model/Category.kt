package com.hafizhmo.cuanaku.model

data class Category(
    val error: Boolean,
    val message: String,
    val categories: List<Categories>
){
    data class Categories(
        val id: Int,
        val slug: String,
        val image: String
    )
}
