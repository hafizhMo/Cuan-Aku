package com.hafizhmo.cuanaku.ui.activities.transaction

import com.hafizhmo.cuanaku.model.Category
import com.hafizhmo.cuanaku.model.Transaction
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionPresenter(val transactionView: TransactionView) {

    fun createTransaction(amount: Int, categoryId: Int, userId: Int, token: String) {
        val call = ApiClient.apiService.createTransaction(amount, categoryId, userId, token)

        call.enqueue(object : Callback<Transaction> {
            override fun onResponse(call: Call<Transaction>, response: Response<Transaction>) {
                val result = response.body()!!

                if (result.error) {
                    transactionView.submitFailed(result.message)
                    return
                }
                transactionView.submitSuccess(result.message)
            }

            override fun onFailure(call: Call<Transaction>, t: Throwable) {
                transactionView.submitFailed(t.toString())
            }
        })
    }

    fun saveTransaction(amount: Int, categoryId: Int, transactionId: Int, token: String) {
        val call =
            ApiClient.apiService.editCurrentTransaction(amount, categoryId, transactionId, token)

        call.enqueue(object : Callback<Transaction> {
            override fun onResponse(call: Call<Transaction>, response: Response<Transaction>) {
                val result = response.body()!!

                if (result.error) {
                    transactionView.editNotFound(result.message)
                    return
                }
                transactionView.editSuccess(result.message)
            }

            override fun onFailure(call: Call<Transaction>, t: Throwable) {
                transactionView.editFailed(t.toString())
            }
        })

    }

    fun deleteTransaction(id: Int) {
        val call = ApiClient.apiService.removeCurrentTransaction(id)

        call.enqueue(object : Callback<Transaction> {
            override fun onResponse(call: Call<Transaction>, response: Response<Transaction>) {
                val result = response.body()!!

                if (result.error) {
                    transactionView.deleteNotFound(result.message)
                    return
                }
                transactionView.deleteSuccess(result.message)
            }

            override fun onFailure(call: Call<Transaction>, t: Throwable) {
                transactionView.deleteFailed(t.toString())
            }
        })
    }

    fun getCategories(){
        val call = ApiClient.apiService.getCategories()

        call.enqueue(object : Callback<Category>{
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                val result = response.body()!!

                if (result.error){
                    transactionView.categoryEmpty(result.message)
                    return
                }

                transactionView.categorySuccess(result.categories, result.message)
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                transactionView.categoryFailed(t.toString())
            }
        })
    }
}