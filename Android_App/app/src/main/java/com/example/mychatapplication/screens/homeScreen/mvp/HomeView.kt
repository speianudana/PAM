package com.example.mychatapplication.screens.homeScreen.mvp

import android.content.Context
import android.view.MenuItem
import android.widget.FrameLayout
import com.example.mychatapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class HomeView(context: Context) : FrameLayout(context) {

  private val bottomNavigation: BottomNavigationView
  private val loadEventsInfoSubject = PublishSubject.create<MenuItem>()

  init {
    inflate(getContext(), R.layout.activity_home, this)
    bottomNavigation = findViewById(R.id.bottom_navigation)
    bottomNavigation.setOnNavigationItemSelectedListener { item ->
        loadEventsInfoSubject.onNext(item)
        true
    }

  }

  fun showHomeScreen() {
    bottomNavigation.selectedItemId = R.id.navigation_home
  }

  fun observeNavigationItemClick(): Observable<MenuItem> {
    return loadEventsInfoSubject
  }
}
