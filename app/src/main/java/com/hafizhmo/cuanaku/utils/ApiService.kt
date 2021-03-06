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

    @GET("user/{id}")
    fun getUserDetail(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Call<Auth>

    //---------------------------- BUDGETING ----------------------------

    @GET("budgeting/user/{id}/latest")
    fun getLatestBudget(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Call<Budgetings>

    @GET("budgeting/user/{id}")
    fun getAllBudget(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Call<Budgetingss>

    @FormUrlEncoded
    @PUT("budgeting")
    fun editCurrentBudget(
        @Field("id") id: Int,
        @Field("total_budget") totalBudget: Int,
        @Header("Authorization") token: String
    ): Call<Budgeting>

    @FormUrlEncoded
    @POST("budgeting")
    fun createBudget(
        @Field("user_id") userId: Int,
        @Field("month") month: String,
        @Field("year") year: String,
        @Field("total_budget") budgetAmount: Int,
        @Header("Authorization") token: String
    ): Call<Budgeting>

    //---------------------------- TRANSACTION ----------------------------

    @GET("transaction/{id}")
    fun getAllTransaction(
        @Path("id") userId: Int,
        @Header("Authorization") token: String
    ): Call<Transactions>

    @GET("transaction/group/{id}")
    fun getAllTransactionGroup(
        @Path("id") userId: Int,
        @Header("Authorization") token: String
    ): Call<TransactionsGroup>

    @FormUrlEncoded
    @POST("transaction")
    fun createTransaction(
        @Field("amount") amount: Int,
        @Field("category_id") categoryId: Int,
        @Field("user_id") userId: Int,
        @Header("Authorization") token: String
    ): Call<Transaction>

    @FormUrlEncoded
    @PUT("transaction")
    fun editCurrentTransaction(
        @Field("amount") amount: Int,
        @Field("category_id") categoryId: Int,
        @Field("id") id: Int,
        @Field("user_id") user_id: Int,
        @Header("Authorization") token: String
    ): Call<Transaction>

    @DELETE("transaction/{id}")
    fun removeCurrentTransaction(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Call<Transaction>

    //---------------------------- RELATION ----------------------------

    @GET("relation/{role}/{id}")
    fun getAllRelation(
        @Path("role") role: String,
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Call<Relations>

    @FormUrlEncoded
    @POST("relation/{role}/create")
    fun createRelation(
        @Path("role") role: String,
        @Field("wali_id") waliId: Int,
        @Field("beban_id") bebanId: Int,
        @Header("Authorization") token: String
    ): Call<Relation>

    @FormUrlEncoded
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
        const val BASE_URL = "http://192.168.10.106"
        const val URL_API = "${BASE_URL}/api/"
        const val URL_IMAGE = "${BASE_URL}/storage/"
    }
}