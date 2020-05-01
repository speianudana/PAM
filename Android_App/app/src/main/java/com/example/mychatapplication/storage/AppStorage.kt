package com.example.mychatapplication.storage

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.example.mychatapplication.models.AuthToken

class AppStorage(var context: Context) {

    companion object {
        public const val BASE_URL = "https://cbostan-57987f03.localhost.run"
        public const val SHARED_PREFERENCES_ID = "shared_preferences"
        public const val TOKEN = "token"
        public const val USER_ID = "user_id"
    }

    fun saveToken(authToken: AuthToken) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_ID, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN, authToken.token)
        editor.putString(USER_ID, authToken.id)
        editor.apply()
    }

    fun removeToken() {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_ID, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN, "")
        editor.putString(USER_ID, "")
        editor.apply()
    }

    fun getToken(): AuthToken? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_ID, Context.MODE_PRIVATE)
        val token = sharedPreferences.getString(TOKEN, "")
        val userId = sharedPreferences.getString(USER_ID, "")
        return if (TextUtils.isEmpty(token) && TextUtils.isEmpty(userId)) {
            null
        } else {
            AuthToken(userId!!, token!!)
        }
    }

}