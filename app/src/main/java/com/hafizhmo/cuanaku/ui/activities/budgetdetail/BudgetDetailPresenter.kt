package com.hafizhmo.cuanaku.ui.activities.budgetdetail

import com.hafizhmo.cuanaku.model.Budgeting
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BudgetDetailPresenter(val budgetDetailView: BudgetDetailView) {

    fun saveBudget(id: Int, totalBudget: Int, token: String){
        val call = ApiClient.apiService.editCurrentBudget(id, totalBudget, token)

        call.enqueue(object : Callback<Budgeting> {
            override fun onResponse(call: Call<Budgeting>, response: Response<Budgeting>) {
                val result = response.body()!!

                if(result.error){
                    budgetDetailView.editNotFound(result.message)
                    return
                }
                budgetDetailView.editSuccess(result.message)
            }

            override fun onFailure(call: Call<Budgeting>, t: Throwable) {
                budgetDetailView.editFailed(t.toString())
            }
        })
    }

    fun createBudget(totalBudget: Int, userId: Int, token: String){
        val call = ApiClient.apiService.createBudget(userId, totalBudget, token)

        call.enqueue(object : Callback<Budgeting> {
            override fun onResponse(call: Call<Budgeting>, response: Response<Budgeting>) {
                val result = response.body()!!

                if(result.error){
                    budgetDetailView.createFailed(result.message)
                    return
                }
                budgetDetailView.createSuccess(result.message)
            }

            override fun onFailure(call: Call<Budgeting>, t: Throwable) {
                budgetDetailView.createFailed(t.toString())
            }
        })
    }

}