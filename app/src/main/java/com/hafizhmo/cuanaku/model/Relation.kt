package com.hafizhmo.cuanaku.model

data class Relation(
    val error: Boolean,
    val message: String,
    val relations: List<Relations>
){
    data class Relations(
        val rid: Int,
        val id: Int,
        val name: String,
        val email: String,
        val status: Int,
        val created_by: Int
    )
}
