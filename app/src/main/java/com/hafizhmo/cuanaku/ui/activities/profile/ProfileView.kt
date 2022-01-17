package com.hafizhmo.cuanaku.ui.activities.profile

import com.hafizhmo.cuanaku.model.Auth

interface ProfileView {

    fun onSuccess(user: Auth.User, msg: String)

    fun onFailed(msg: String)

}