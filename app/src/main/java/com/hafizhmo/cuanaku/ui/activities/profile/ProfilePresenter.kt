package com.hafizhmo.cuanaku.ui.activities.profile

import com.hafizhmo.cuanaku.model.Auth
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePresenter(private val profileView: ProfileView) {

    fun getResponse(id: Int, token: String){
        val call = ApiClient.apiService.getUserDetail(id, "Bearer $token")

        call.enqueue(object : Callback<Auth> {
            override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                val result = response.body()!!

                if (result.error){
                    profileView.onFailed(result.message)
                    return
                }

                profileView.onSuccess(result.user, result.message)
            }

            override fun onFailure(call: Call<Auth>, t: Throwable) {
                profileView.onFailed(t.toString())
            }
        })
    }

}