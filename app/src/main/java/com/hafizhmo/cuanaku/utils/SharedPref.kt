package com.hafizhmo.cuanaku.utils

import android.content.Context
import android.content.SharedPreferences.Editor

class SharedPref(val context: Context) {
    private val KEY_IS_LOGGED_IN = "isLoggedIn"
    private val KEY_ID = "id"
    private val KEY_ROLE = "role"
    private val KEY_TOKEN = "token"
    private val PREF_NAME = "esangu"

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, 0)

    fun isLoggedIn(): Boolean = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)

    fun getSessionId(): Int = sharedPreferences.getInt(KEY_ID, 0)
    fun getSessionRole(): String = sharedPreferences.getString(KEY_ROLE, "").toString()
    fun getSessionToken(): String = sharedPreferences.getString(KEY_TOKEN, "").toString()

    fun saveSession(id: Int, role: String, token: String) {
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_ID, id)
        editor.putString(KEY_ROLE, role)
        editor.putString(KEY_TOKEN, "Bearer $token")
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.apply()
    }

    fun clearSession() {
        val editor: Editor = sharedPreferences.edit()
        editor.remove(KEY_ID)
        editor.remove(KEY_ROLE)
        editor.remove(KEY_TOKEN)
        editor.remove(KEY_IS_LOGGED_IN)
        editor.apply()
    }
}