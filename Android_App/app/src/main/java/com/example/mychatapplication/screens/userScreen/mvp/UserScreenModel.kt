package com.example.mychatapplication.screens.userScreen.mvp

import android.app.Activity
import android.content.Intent
import com.example.mychatapplication.screens.login.LoginActivity
import com.example.mychatapplication.storage.AppStorage

class UserScreenModel(private val activity: Activity, private val appStorage: AppStorage) {

    fun moveToLoginScreen() {
      appStorage.removeToken()
      val intent= Intent(activity, LoginActivity::class.java)
      activity.startActivity(intent)
      activity.finish()
    }

    fun getAuthToken(): String {
        return appStorage.getToken()?.token!!
    }
}
