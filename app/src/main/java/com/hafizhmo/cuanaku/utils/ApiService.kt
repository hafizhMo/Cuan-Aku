package com.hafizhmo.cuanaku.utils

import com.hafizhmo.cuanaku.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //---------------------------- CATEGORY ----------------------------

    @GET("category")
    fun getCategories(): Call<Category>

    //---------------------------- AUTHENTICATION ----------------------------

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Auth>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("role") role: Int
    ): Call<Auth>

    //---------------------------- BUDGETING ----------------------------

    @GET("budgeting/user/{id}/latest")
    fun getLatestBudget(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Call<Budgeting>

    @GET("budgeting/user/{id}")
    fun getAllBudget(
        @Path("id") id: Int
    ): Call<Budgeting>

    @PUT("budgeting")
    fun editCurrentBudget(
        @Field("id") id: Int,
        @Field("total_budget") totalBudget: Int
    ): Call<Budgeting>

    @POST("budgeting")
    fun createBudget(
        @Field("user_id") userId: Int,
        @Field("total_budget") budgetAmount: Int
    ): Call<Budgeting>

    //---------------------------- TRANSACTION ----------------------------

    @GET("transaction/{id}")
    fun getAllTransaction(
        @Path("id") userId: Int
    ): Call<Transaction>

    @POST("transaction")
    fun createTransaction(
        @Field("amount") amount: Int,
        @Field("category_id") categoryId: Int,
        @Field("user_id") userId: Int,
    ): Call<Transaction>

    @PUT("transaction")
    fun editCurrentTransaction(
        @Field("amount") amount: Int,
        @Field("category_id") categoryId: Int,
        @Field("id") id: Int
    ): Call<Transaction>

    @DELETE("transaction/{id}")
    fun removeCurrentTransaction(
        @Path("id") id: Int
    ): Call<Transaction>

    //---------------------------- RELATION ----------------------------

    @GET("relation/{role}/{id}")
    fun getAllRelation(
        @Path("role") role: String,
        @Path("id") id: Int
    ): Call<Relation>

    @POST("relation/create-{role}")
    fun createRelation(
        @Path("role") role: String,
        @Field("wali_id") waliId: Int,
        @Field("beban_id") bebanId: Int,
    ): Call<Relation>

    @PUT("relation/update")
    fun editStatusRelation(
        @Field("id") id: Int,
        @Field("status") status: Int
    ): Call<Relation>

    @DELETE("relation/delete/{id}")
    fun removeRelation(
        @Path("id") id: Int
    ): Call<Relation>

    companion object {
        const val BASE_URL = "http://192.168.10.106/api/"
    }
}