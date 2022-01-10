package com.hafizhmo.cuanaku.utils

import com.hafizhmo.cuanaku.utils.ApiService.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {

    private val retrofitClient: Retrofit.Builder by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    val apiService: ApiService by lazy {
        retrofitClient.build().create(ApiService::class.java)
    }
}