package com.example.mychatapplication.screens.login.mvp

import android.text.TextUtils
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.models.AuthenticateUser
import com.example.mychatapplication.util.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class LoginPresenter(private val model: LoginModel, private val view: LoginView, private val rxSchedulers: RxSchedulers, private val serverRequestService: ServerRequestService) {

  private val subscriptions = CompositeDisposable()

  fun onCreate() {
    subscriptions.add(loginSubscription())
  }

  private fun loginSubscription(): Disposable = view.observeLoginButtonClicks()
      .observeOn(rxSchedulers.background())
      .filter { !TextUtils.isEmpty(it.first) && !TextUtils.isEmpty(it.second) }
      .flatMap {
          val authenticateUser = AuthenticateUser(it.first, it.second)
          serverRequestService.authenticate(authenticateUser)
      }
      .observeOn(rxSchedulers.network())
      .subscribe ({
        model.moveToNextScreen(it)
      }, {
          view.showError()
      })

  fun onDestroy() {
    subscriptions.dispose()
  }
}