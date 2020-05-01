package com.example.mychatapplication.screens.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.application.EventApp
import com.example.mychatapplication.screens.login.mvp.LoginPresenter
import com.example.mychatapplication.screens.login.mvp.LoginView
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: LoginPresenter

    @Inject
    lateinit var view: LoginView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerLoginComponent.builder()
            .loginModule(LoginModule(this))
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
