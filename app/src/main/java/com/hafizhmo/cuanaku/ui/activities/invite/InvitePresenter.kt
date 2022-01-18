package com.hafizhmo.cuanaku.ui.activities.invite

import com.hafizhmo.cuanaku.model.Relation
import com.hafizhmo.cuanaku.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvitePresenter(val inviteView: InviteView) {

    fun invite(role: String, waliId: Int, bebanId: Int, token: String){
        val call = ApiClient.apiService.createRelation(role, waliId, bebanId, token)

        call.enqueue(object : Callback<Relation> {
            override fun onResponse(call: Call<Relation>, response: Response<Relation>) {
                val result = response.body()!!

                if (result.error){
                    inviteView.inviteNotFound(result.message)
                }

                inviteView.inviteSuccess(result.message)
            }

            override fun onFailure(call: Call<Relation>, t: Throwable) {
                inviteView.inviteFailed(t.toString())
            }
        })
    }
}