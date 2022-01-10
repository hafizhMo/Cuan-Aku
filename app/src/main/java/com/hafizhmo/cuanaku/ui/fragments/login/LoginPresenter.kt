package com.hafizhmo.cuanaku.ui.fragments.login

import com.hafizhmo.cuanaku.model.Auth
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val loginView: LoginView) {

    fun login(email: String, password: String) {
        val call = ApiClient.apiService.login(email, password)

        call.enqueue(object : Callback<Auth> {
            override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                val result = response.body()!!

                if (result.error) {
                    loginView.onFailed(result.message)
                    return
                }

                loginView.onSuccess(result.user, result.message)
            }

            override fun onFailure(call: Call<Auth>, t: Throwable) {
                loginView.onFailed(t.toString())
            }
        })
    }
}