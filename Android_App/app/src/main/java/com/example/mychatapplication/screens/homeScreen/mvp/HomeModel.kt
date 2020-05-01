package com.example.mychatapplication.screens.homeScreen.mvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mychatapplication.R
import com.example.mychatapplication.screens.settings.SettingsActivity

class HomeModel(private val activity: AppCompatActivity) {

  fun showSettings() {
    val intent= Intent(activity,
      SettingsActivity::class.java)
    activity.startActivity(intent)
  }

  fun loadFragment(fragment: Fragment) {
    val transaction = activity.supportFragmentManager.beginTransaction()
    transaction.replace(R.id.fragment_holder, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
  }
}
