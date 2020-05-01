package com.example.mychatapplication.screens.settings.mvp

import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.R
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable


class SettingsView(var activity: AppCompatActivity) : FrameLayout(activity) {

  private val appSettings:Button
  private val userId:TextView
  val username: TextView

  init {
    inflate(activity, R.layout.settings_layout, this)
    appSettings = findViewById(R.id.app_settings)
    userId = findViewById(R.id.user_id)
    username = findViewById(R.id.username)
    if (activity.supportActionBar != null){
      activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
      activity.supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
  }

  fun observeAppSettingsClicks(): Observable<Any> = RxView.clicks(appSettings)

  fun setData(id: String, userName: String) {
    activity.runOnUiThread {
      userId.text = context.getString(R.string.user_id_label, id)
      username.text = context.getString(R.string.username_label, userName)
    }
  }

  fun showError() {
    activity.runOnUiThread {
      Toast.makeText(activity, R.string.network_connection_label, Toast.LENGTH_LONG).show()
    }
  }
}
