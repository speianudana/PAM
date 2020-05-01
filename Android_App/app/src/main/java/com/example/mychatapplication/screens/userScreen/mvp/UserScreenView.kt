package com.example.mychatapplication.screens.userScreen.mvp

import android.view.View
import android.widget.*
import androidx.fragment.app.FragmentActivity
import com.example.mychatapplication.R
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable

class UserScreenView(var activity: FragmentActivity) : FrameLayout(activity) {

    private val view: View

    val username: TextView
    val logoutButton: Button
    val progressBar: ProgressBar

    init {
        view = inflate(activity, R.layout.user_info, this)
        username = view.findViewById(R.id.username)
        logoutButton = view.findViewById(R.id.logout)
        progressBar = view.findViewById(R.id.progressBar)
    }

    fun observeLogoutButtonClicks(): Observable<Any> = RxView.clicks(logoutButton)

    fun showProgressBar(showProgress: Boolean) {
        activity.runOnUiThread {
            if (showProgress) {
                progressBar.visibility = View.VISIBLE
            }
            else {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }

    fun setUsername(usernameValue: String) {
        activity.runOnUiThread {
            username.text = context.getString(R.string.hi_label, usernameValue)
        }
    }

    fun showError() {
        activity.runOnUiThread {
            Toast.makeText(context, R.string.network_connection_label, Toast.LENGTH_LONG).show()
        }
    }
}
