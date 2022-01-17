package com.hafizhmo.cuanaku.ui.fragments.dashboard

import com.hafizhmo.cuanaku.model.Budgetings
import com.hafizhmo.cuanaku.model.TransactionsGroup
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

    fun getTransactionGroup(id: Int, token: String){
        val call = ApiClient.apiService.getAllTransactionGroup(id, token)

        call.enqueue(object :Callback<TransactionsGroup>{
            override fun onResponse(
                call: Call<TransactionsGroup>,
                response: Response<TransactionsGroup>
            ) {
                val result = response.body()!!

                if (result.error){
                    dashboardView.onGroupFailed(result.message)
                    return
                }

                dashboardView.onGroupSuccess(result.transactions, result.message)
            }

            override fun onFailure(call: Call<TransactionsGroup>, t: Throwable) {
                dashboardView.onGroupFailed(t.toString())
            }
        })
    }
}