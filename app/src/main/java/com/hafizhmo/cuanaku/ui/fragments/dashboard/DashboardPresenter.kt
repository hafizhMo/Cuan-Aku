package com.hafizhmo.cuanaku.ui.fragments.dashboard

import com.hafizhmo.cuanaku.model.Budgetings
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardPresenter(val dashboardView: DashboardView) {

    fun getLatestBudget(id: Int, token: String){
        val call = ApiClient.apiService.getLatestBudget(id, token)

        call.enqueue(object : Callback<Budgetings>{
            override fun onResponse(call: Call<Budgetings>, response: Response<Budgetings>) {
                val result = response.body()!!

                if (result.error){
                    dashboardView.onEmpty(result.message)
                    return
                }

                dashboardView.onSuccess(result.budget, result.message)
            }

            override fun onFailure(call: Call<Budgetings>, t: Throwable) {
                dashboardView.onFailed(t.toString())
            }
        })
    }
}