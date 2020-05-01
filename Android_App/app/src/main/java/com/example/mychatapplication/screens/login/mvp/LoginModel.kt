package com.example.mychatapplication.screens.login.mvp

import android.app.Activity
import android.content.Intent
import com.example.mychatapplication.models.AuthToken
import com.example.mychatapplication.screens.homeScreen.HomeActivity
import com.example.mychatapplication.storage.AppStorage

class LoginModel(private val activity: Activity, private val appStorage: AppStorage) {

  fun moveToNextScreen(authToken: AuthToken) {
    appStorage.saveToken(authToken)
    val intent= Intent(activity, HomeActivity::class.java)
    activity.startActivity(intent)
    activity.finish()
  }
}
