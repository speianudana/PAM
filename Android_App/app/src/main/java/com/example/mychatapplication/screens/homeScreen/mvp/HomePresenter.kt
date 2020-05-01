package com.example.mychatapplication.screens.homeScreen.mvp

import com.example.mychatapplication.R
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.screens.events.EventsFragment
import com.example.mychatapplication.screens.userScreen.UserFragment
import com.example.mychatapplication.util.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class HomePresenter(private val model: HomeModel, private val view: HomeView, private val rxSchedulers: RxSchedulers, private val serverRequestService: ServerRequestService) {

  private val subscriptions = CompositeDisposable()
  private val homeFragment = EventsFragment()
  private val userFragment = UserFragment()

  fun onCreate() {
    subscriptions.add(navigationSubscription())
    view.showHomeScreen()
  }

    private fun navigationSubscription(): Disposable = view.observeNavigationItemClick()
        .observeOn(rxSchedulers.background())
        .subscribe {
            when (it.itemId) {
                R.id.navigation_home -> model.loadFragment(homeFragment)
                R.id.navigation_user -> model.loadFragment(userFragment)
            }
        }

  fun showSettings() {
      model.showSettings()
  }

  fun onDestroy() {
    subscriptions.dispose()
  }
}