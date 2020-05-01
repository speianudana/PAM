package com.example.mychatapplication.screens.splashScreen

import com.example.mychatapplication.application.AppComponent
import com.example.mychatapplication.screens.splashScreen.mvp.SplashScreenModel
import com.example.mychatapplication.screens.splashScreen.mvp.SplashScreenPresenter
import com.example.mychatapplication.screens.splashScreen.mvp.SplashScreenView
import com.example.mychatapplication.storage.AppStorage
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class SplashScreenScope

@SplashScreenScope
@Component(modules = [SplashScreenModule::class], dependencies = [AppComponent::class])
interface SplashScreenComponent {

  fun inject(activity: SplashScreenActivity)
}

@Module
class SplashScreenModule(private val activity: SplashScreenActivity) {

  @SplashScreenScope
  @Provides
  fun view() = SplashScreenView(activity)

  @SplashScreenScope
  @Provides
  fun model(appStorage: AppStorage) = SplashScreenModel(activity, appStorage)

  @SplashScreenScope
  @Provides
  fun presenter(view: SplashScreenView, model: SplashScreenModel) = SplashScreenPresenter(model, view)
}