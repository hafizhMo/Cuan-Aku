package com.hafizhmo.cuanaku.utils

import android.content.Context
import android.content.SharedPreferences.*
import com.hafizhmo.cuanaku.model.Auth

class SharedPref(val context: Context) {
    private val KEY_IS_LOGGED_IN = "isLoggedIn"
    private val KEY_ID = "id"
    private val KEY_NAME = "name"
    private val KEY_EMAIl = "email"
    private val KEY_ROLE = "role"
    private val PREF_NAME = "osemar"

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, 0)

    fun isLoggedIn(): Boolean = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)

    fun getSessionId(): Int = sharedPreferences.getInt(KEY_ID, 0)

    fun saveSession(user: Auth.User) {
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_ID, user.id)
        editor.putString(KEY_EMAIl, user.email)
        editor.putString(KEY_NAME, user.name)
        editor.putString(KEY_ROLE, user.role)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.apply()
    }

    fun clearSession() {
        val editor: Editor = sharedPreferences.edit()
        editor.remove(KEY_ID)
        editor.remove(KEY_EMAIl)
        editor.remove(KEY_NAME)
        editor.remove(KEY_ROLE)
        editor.remove(KEY_IS_LOGGED_IN)
        editor.apply()
    }
}