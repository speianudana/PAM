package com.example.mychatapplication.screens.login

import android.app.Activity
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.application.AppComponent
import com.example.mychatapplication.screens.login.mvp.LoginModel
import com.example.mychatapplication.screens.login.mvp.LoginPresenter
import com.example.mychatapplication.screens.login.mvp.LoginView
import com.example.mychatapplication.storage.AppStorage
import com.example.mychatapplication.util.RxSchedulers
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class LoginScope

@LoginScope
@Component(modules = [LoginModule::class], dependencies = [AppComponent::class])
interface LoginComponent {

  fun inject(activity: LoginActivity)
}

@Module
class LoginModule(private val activity: Activity) {

  @LoginScope
  @Provides
  fun view() = LoginView(activity)

  @LoginScope
  @Provides
  fun model(appStorage: AppStorage) = LoginModel(activity, appStorage)

  @LoginScope
  @Provides
  fun presenter(view: LoginView, model: LoginModel, rxSchedulers: RxSchedulers, serverRequestService: ServerRequestService) = LoginPresenter(model, view, rxSchedulers, serverRequestService)
}