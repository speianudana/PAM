package com.example.mychatapplication.screens.login.mvp

import android.app.Activity
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.example.mychatapplication.R
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable


class LoginView(val activity: Activity) : FrameLayout(activity) {

  private val loginButton: Button
  private val username: EditText
  private val password: EditText

  init {
    inflate(activity, R.layout.activity_login, this)
    loginButton = findViewById(R.id.login_button_id)
    username = findViewById(R.id.username)
    password = findViewById(R.id.password)
  }

  fun observeLoginButtonClicks(): Observable<Pair<String, String>> = RxView.clicks(loginButton).map {
    showEmptyDataError()
    Pair(getUsernameText(), getPasswordText())
  }

  private fun getUsernameText(): String {
    return username.text.toString()
  }

  private fun getPasswordText(): String {
    return password.text.toString()
  }

  fun showError() {
    activity.runOnUiThread {
      Toast.makeText(context, context.getString(R.string.network_error_label), Toast.LENGTH_LONG).show()
    }
  }

  private fun showEmptyDataError() {
    if(username.text.toString().isEmpty()) {
      username.error = context.getString(R.string.user_name_error)
    }
    if(password.text.toString().isEmpty()) {
      password.error = context.getString(R.string.password_error)
    }
  }
}
