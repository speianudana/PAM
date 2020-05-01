package com.example.mychatapplication.screens.splashScreen.mvp

import android.os.Handler
import io.reactivex.disposables.CompositeDisposable

class SplashScreenPresenter(private val model: SplashScreenModel, private val view: SplashScreenView) {

    private val subscriptions = CompositeDisposable()

    fun onCreate() {
        model.setAnimation()
        Handler().postDelayed({
            view.startAnimation()
            Handler().postDelayed({
                view.setLogoGone()
                model.moveToNextScreen()
            }, 500)
        }, 1500)
    }

    fun onDestroy() {
        subscriptions.dispose()
    }
}