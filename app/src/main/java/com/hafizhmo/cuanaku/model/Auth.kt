package com.hafizhmo.cuanaku.model

data class Auth(
    val error: Boolean,
    val message: String,
    val user: User,
    val token: String
){
    data class User(
        val id: Int,
        val name: String,
        val email: String,
        val role: String,
        val profile_photo_url: String
    )
}
