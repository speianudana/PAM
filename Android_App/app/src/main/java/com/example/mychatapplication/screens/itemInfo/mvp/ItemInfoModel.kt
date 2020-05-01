package com.example.mychatapplication.screens.itemInfo.mvp

import android.app.Activity
import com.example.mychatapplication.storage.AppStorage

class ItemInfoModel(private val activity: Activity, private val appStorage: AppStorage) {

  fun getAuthToken(): String {
    return appStorage.getToken()?.token!!
  }

  fun finish() {
    activity.finish()
  }
}
