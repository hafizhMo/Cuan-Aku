package com.hafizhmo.cuanaku.ui.activities.budget

import com.hafizhmo.cuanaku.model.Budgetings
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BudgetPresenter(val budgetView: BudgetView) {

    fun getAllBudget(id: Int){
        val call = ApiClient.apiService.getAllBudget(id)

        call.enqueue(object : Callback<Budgetings> {
            override fun onResponse(call: Call<Budgetings>, response: Response<Budgetings>) {
                val result = response.body()!!

                if (result.error){
                    budgetView.onEmpty(result.message)
                    return
                }

                budgetView.onSuccess(result.budget, result.message)
            }

            override fun onFailure(call: Call<Budgetings>, t: Throwable) {
                budgetView.onFailed(t.toString())
            }
        })
    }
}