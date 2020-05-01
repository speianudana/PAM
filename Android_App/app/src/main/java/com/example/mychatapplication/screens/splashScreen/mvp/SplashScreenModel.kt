package com.example.mychatapplication.screens.splashScreen.mvp

import android.app.Activity
import android.content.Intent
import com.example.mychatapplication.R
import com.example.mychatapplication.screens.homeScreen.HomeActivity
import com.example.mychatapplication.screens.login.LoginActivity
import com.example.mychatapplication.storage.AppStorage

class SplashScreenModel(private val activity: Activity, private val appStorage: AppStorage) {

  fun setAnimation() {
    activity.overridePendingTransition(
      R.anim.fade_in,
      R.anim.fade_out
    )
  }

  fun moveToNextScreen() {
    if (appStorage.getToken() ==null) {
      activity.startActivity(Intent(activity, LoginActivity::class.java))
    } else {
      activity.startActivity(Intent(activity, HomeActivity::class.java))
    }
    activity.finish()
  }
}
