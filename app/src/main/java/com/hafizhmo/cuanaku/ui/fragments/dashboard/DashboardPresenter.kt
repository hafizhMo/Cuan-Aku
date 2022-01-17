package com.hafizhmo.cuanaku.ui.fragments.dashboard

import com.hafizhmo.cuanaku.model.Budgeting
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardPresenter(val dashboardView: DashboardView) {

    fun getLatestBudget(id: Int, token: String){
        val call = ApiClient.apiService.getLatestBudget(id, "Bearer $token")

        call.enqueue(object : Callback<Budgeting>{
            override fun onResponse(call: Call<Budgeting>, response: Response<Budgeting>) {
                val result = response.body()!!

                if (result.error){
                    dashboardView.onEmpty(result.message)
                    return
                }

                dashboardView.onSuccess(result.budget, result.message)
            }

            override fun onFailure(call: Call<Budgeting>, t: Throwable) {
                dashboardView.onFailed(t.toString())
            }
        })
    }
}