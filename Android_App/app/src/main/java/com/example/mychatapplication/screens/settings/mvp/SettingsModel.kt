package com.example.mychatapplication.screens.settings.mvp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.example.mychatapplication.storage.AppStorage

class SettingsModel(private val activity: Activity, private val appStorage: AppStorage) {

  fun finish() {
    activity.finish()
  }

  fun openSettings() {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri = Uri.fromParts("package", activity.packageName, null)
    intent.data = uri
    activity.startActivity(intent)
  }

  fun getAuthToken(): String {
    return appStorage.getToken()?.token!!
  }
}
