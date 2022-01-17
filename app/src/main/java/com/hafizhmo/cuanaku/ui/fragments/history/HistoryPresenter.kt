package com.hafizhmo.cuanaku.ui.fragments.history

import com.hafizhmo.cuanaku.model.Transactions
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryPresenter(val historyView: HistoryView) {

    fun getAllHistory(id: Int){
        val call = ApiClient.apiService.getAllTransaction(id)

        call.enqueue(object : Callback<Transactions> {
            override fun onResponse(call: Call<Transactions>, response: Response<Transactions>) {
                val result = response.body()!!

                if (result.error){
                    historyView.onEmpty(result.message)
                    return
                }

                historyView.onSuccess(result.transactions, result.message)
            }

            override fun onFailure(call: Call<Transactions>, t: Throwable) {
                historyView.onFailed(t.toString())
            }

        })
    }
}