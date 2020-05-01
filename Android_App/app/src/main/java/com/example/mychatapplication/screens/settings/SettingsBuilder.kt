package com.example.mychatapplication.screens.settings

import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.application.AppComponent
import com.example.mychatapplication.screens.settings.mvp.SettingsModel
import com.example.mychatapplication.screens.settings.mvp.SettingsPresenter
import com.example.mychatapplication.screens.settings.mvp.SettingsView
import com.example.mychatapplication.storage.AppStorage
import com.example.mychatapplication.util.RxSchedulers
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class SettingScope

@SettingScope
@Component(modules = [SettingsModule::class], dependencies = [AppComponent::class])
interface SettingComponent {

  fun inject(activity: SettingsActivity)
}

@Module
class SettingsModule(private val activity: AppCompatActivity) {

  @SettingScope
  @Provides
  fun view() = SettingsView(activity)

  @SettingScope
  @Provides
  fun model(appStorage: AppStorage) = SettingsModel(activity, appStorage)

  @SettingScope
  @Provides
  fun presenter(view: SettingsView, model: SettingsModel, rxSchedulers: RxSchedulers, serverRequestService: ServerRequestService) = SettingsPresenter(model, view, rxSchedulers, serverRequestService)
}