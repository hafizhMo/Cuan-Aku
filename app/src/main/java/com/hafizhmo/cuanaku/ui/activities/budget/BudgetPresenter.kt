package com.hafizhmo.cuanaku.ui.activities.budget

import com.hafizhmo.cuanaku.model.Budgeting
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BudgetPresenter(val budgetView: BudgetView) {

    fun getAllBudget(id: Int){
        val call = ApiClient.apiService.getAllBudget(id)

        call.enqueue(object : Callback<Budgeting> {
            override fun onResponse(call: Call<Budgeting>, response: Response<Budgeting>) {
                val result = response.body()!!

                if (result.error){
                    budgetView.onEmpty(result.message)
                    return
                }

                budgetView.onSuccess(result.budget, result.message)
            }

            override fun onFailure(call: Call<Budgeting>, t: Throwable) {
                budgetView.onFailed(t.toString())
            }
        })
    }
}