package com.example.mychatapplication.screens.userScreen

import androidx.fragment.app.FragmentActivity
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.application.AppComponent
import com.example.mychatapplication.screens.userScreen.mvp.UserScreenModel
import com.example.mychatapplication.screens.userScreen.mvp.UserScreenPresenter
import com.example.mychatapplication.screens.userScreen.mvp.UserScreenView
import com.example.mychatapplication.storage.AppStorage
import com.example.mychatapplication.util.RxSchedulers
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class UserScreenScope

@UserScreenScope
@Component(modules = [UserScreenModule::class], dependencies = [AppComponent::class])
interface UserScreenComponent {

  fun inject(fragment: UserFragment)
}

@Module
class UserScreenModule(private val activity: FragmentActivity) {

  @UserScreenScope
  @Provides
  fun view() = UserScreenView(activity)

  @UserScreenScope
  @Provides
  fun model(appStorage: AppStorage) = UserScreenModel(activity, appStorage)

  @UserScreenScope
  @Provides
  fun presenter(view: UserScreenView, model: UserScreenModel, rxSchedulers: RxSchedulers, serverRequestService: ServerRequestService) = UserScreenPresenter(model, view, rxSchedulers, serverRequestService)
}