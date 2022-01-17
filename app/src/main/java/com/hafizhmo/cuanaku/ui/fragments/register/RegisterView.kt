package com.hafizhmo.cuanaku.ui.fragments.register

import com.hafizhmo.cuanaku.model.Auth

interface RegisterView {

    fun onSuccess(user: Auth.User, token: String, message: String)

    fun onFailed(message: String)

    fun onAlreadyExists(message: String)

}