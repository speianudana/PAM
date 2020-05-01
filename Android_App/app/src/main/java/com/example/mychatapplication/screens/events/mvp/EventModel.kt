package com.example.mychatapplication.screens.events.mvp

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.example.mychatapplication.screens.itemInfo.ItemInfoActivity
import com.example.mychatapplication.storage.AppStorage

class EventModel(private val activity: FragmentActivity, private val appStorage: AppStorage) {

  fun showItemInfo(id: String) {
    val intent= Intent(activity, ItemInfoActivity::class.java)
    intent.putExtra("id", id)
    activity.startActivity(intent)
  }

  fun getAuthToken(): String {
    return appStorage.getToken()?.token!!
  }
}
