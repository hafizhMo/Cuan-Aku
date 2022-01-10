package com.hafizhmo.cuanaku.ui.fragments.register

import com.hafizhmo.cuanaku.model.Auth
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter(val registerView: RegisterView) {

    fun register(name: String, email: String, password: String, role: Int){
        val call = ApiClient.apiService.register(name, email, password, role)

        call.enqueue(object : Callback<Auth>{
            override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                val result = response.body()!!

                if (result.error){
                    registerView.onAlreadyExists(result.message)
                    return
                }

                registerView.onSuccess(result.user, response.message())
            }

            override fun onFailure(call: Call<Auth>, t: Throwable) {
                registerView.onFailed(t.toString())
            }
        })
    }
}