package com.example.mychatapplication

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.example.mychatapplication.models.AuthToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {

    companion object {

        private var retrofit: Retrofit? = null
        private const val BASE_URL = "https://user-57987f03.localhost.run"
        private const val SHARED_PREFERENCES_ID = "shared_preferences"
        private const val TOKEN = "token"
        private const val USER_ID = "user_id"

        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun saveToken(authToken: AuthToken, context: Context) {
            val sharedPreferences:SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_ID, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(TOKEN, authToken.token)
            editor.putString(USER_ID, authToken.id)
            editor.apply()
        }

        fun removeToken(context: Context) {
            val sharedPreferences:SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_ID, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(TOKEN, "")
            editor.putString(USER_ID, "")
            editor.apply()
        }

        fun getToken(context: Context): AuthToken? {
            val sharedPreferences:SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_ID, Context.MODE_PRIVATE)
            val token = sharedPreferences.getString(TOKEN, "")
            val userId= sharedPreferences.getString(USER_ID, "")
            return if (TextUtils.isEmpty(token) && TextUtils.isEmpty(userId)) {
                null
            } else {
                AuthToken(userId!!, token!!)
            }
        }
    }
}