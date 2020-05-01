package com.example.mychatapplication.application

import android.app.Application
import android.content.Context
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.storage.AppStorage
import com.example.mychatapplication.util.RxSchedulers
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class AppScope

@AppScope
@Component(modules = [NetworkModule::class, RxModule::class, StorageModule::class, AppModule::class])
interface AppComponent {

  fun context(): Context

  fun serverRequestService(): ServerRequestService

  fun rxSchedulers(): RxSchedulers

  fun appStorage(): AppStorage

  fun inject(application: EventApp)
}


@Module
class AppModule(private val application: Application) {

  @Provides
  @AppScope
  fun context() = application.applicationContext
}
