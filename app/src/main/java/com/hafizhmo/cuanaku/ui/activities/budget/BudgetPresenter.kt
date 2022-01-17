package com.hafizhmo.cuanaku.ui.activities.budget

import com.hafizhmo.cuanaku.model.Budgetingss
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BudgetPresenter(val budgetView: BudgetView) {

    fun getAllBudget(id: Int, token: String){
        val call = ApiClient.apiService.getAllBudget(id, token)

        call.enqueue(object : Callback<Budgetingss> {
            override fun onResponse(call: Call<Budgetingss>, response: Response<Budgetingss>) {
                val result = response.body()!!

                if (result.error){
                    budgetView.onEmpty(result.message)
                    return
                }

                budgetView.onSuccess(result.budget, result.message)
            }

            override fun onFailure(call: Call<Budgetingss>, t: Throwable) {
                budgetView.onFailed(t.toString())
            }
        })
    }
}