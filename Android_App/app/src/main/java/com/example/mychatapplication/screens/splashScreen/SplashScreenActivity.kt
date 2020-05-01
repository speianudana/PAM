package com.example.mychatapplication.screens.splashScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.application.EventApp
import com.example.mychatapplication.screens.splashScreen.mvp.SplashScreenPresenter
import com.example.mychatapplication.screens.splashScreen.mvp.SplashScreenView
import javax.inject.Inject

class SplashScreenActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: SplashScreenPresenter

    @Inject
    lateinit var view: SplashScreenView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerSplashScreenComponent.builder()
            .splashScreenModule(SplashScreenModule(this))
            .appComponent(EventApp.get(this).getComponent())
            .build().inject(this)
        setContentView(view)
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}