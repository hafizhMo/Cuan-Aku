package com.hafizhmo.cuanaku.ui.fragments.login

import com.hafizhmo.cuanaku.model.Auth

interface LoginView {
    fun onSuccess(user: Auth.User, token: String, message: String)
    fun onFailed(message: String)
}