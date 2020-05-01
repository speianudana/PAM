package com.example.mychatapplication.screens.homeScreen

import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.application.AppComponent
import com.example.mychatapplication.screens.homeScreen.mvp.HomeModel
import com.example.mychatapplication.screens.homeScreen.mvp.HomePresenter
import com.example.mychatapplication.screens.homeScreen.mvp.HomeView
import com.example.mychatapplication.util.RxSchedulers
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class HomeScope

@HomeScope
@Component(modules = [HomeModule::class], dependencies = [AppComponent::class])
interface HomeComponent {

  fun inject(activity: HomeActivity)
}

@Module
class HomeModule(private val activity: AppCompatActivity) {

  @HomeScope
  @Provides
  fun view() = HomeView(activity)

  @HomeScope
  @Provides
  fun model() = HomeModel(activity)

  @HomeScope
  @Provides
  fun presenter(view: HomeView, model: HomeModel, rxSchedulers: RxSchedulers, serverRequestService: ServerRequestService) = HomePresenter(model, view, rxSchedulers, serverRequestService)
}