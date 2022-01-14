package com.hafizhmo.cuanaku.ui.activities.relation

import com.hafizhmo.cuanaku.model.Relation
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RelationPresenter(val relationView: RelationView) {

    fun getRelations(role: String, id: Int){
        val call = ApiClient.apiService.getAllRelation(role, id)

        call.enqueue(object : Callback<Relation>{
            override fun onResponse(call: Call<Relation>, response: Response<Relation>) {
                val result = response.body()!!

                if (result.error){
                    relationView.getAllEmpty(result.message)
                return
                }

                relationView.getAllSuccess(result.relations, result.message)
            }

            override fun onFailure(call: Call<Relation>, t: Throwable) {
                relationView.getAllFailed(t.toString())
            }
        })
    }

    fun acceptRelation(id: Int){
        val call = ApiClient.apiService.editStatusRelation(id, 1)

        call.enqueue(object : Callback<Relation>{
            override fun onResponse(call: Call<Relation>, response: Response<Relation>) {
                val result = response.body()!!

                if (result.error){
                    relationView.editNotFound(result.message)
                    return
                }

                relationView.editSuccess(result.message)
            }

            override fun onFailure(call: Call<Relation>, t: Throwable) {
                relationView.editFailed(t.toString())
            }
        })
    }

    fun declineRelation(id: Int){
        val call = ApiClient.apiService.editStatusRelation(id, 2)

        call.enqueue(object : Callback<Relation>{
            override fun onResponse(call: Call<Relation>, response: Response<Relation>) {
                val result = response.body()!!

                if (result.error){
                    relationView.editNotFound(result.message)
                    return
                }

                relationView.editSuccess(result.message)
            }

            override fun onFailure(call: Call<Relation>, t: Throwable) {
                relationView.editFailed(t.toString())
            }
        })
    }

    fun deleteRelation(id: Int){
        val call = ApiClient.apiService.removeRelation(id)

        call.enqueue(object : Callback<Relation>{
            override fun onResponse(call: Call<Relation>, response: Response<Relation>) {
                val result = response.body()!!

                if (result.error){
                    relationView.deleteNotFound(result.message)
                    return
                }

                relationView.deleteSuccess(result.message)
            }

            override fun onFailure(call: Call<Relation>, t: Throwable) {
                relationView.deleteFailed(t.toString())
            }
        })
    }

}