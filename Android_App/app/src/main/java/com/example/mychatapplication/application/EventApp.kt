package com.example.mychatapplication.application

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDexApplication

class EventApp : MultiDexApplication() {
  companion object {

    fun get(activity: Activity) = activity.application as EventApp
    fun get(fragment: Fragment) = fragment.requireContext().applicationContext as EventApp
    lateinit var app: EventApp
      private set

    fun appComponent(): AppComponent = app.getComponent()
  }

  private lateinit var appComponent: AppComponent


  override fun onCreate() {
    super.onCreate()
    initBasic()

    appComponent = buildEngageAppComponent()
    appComponent.inject(this)
  }

  private fun initBasic() {
    app = this
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
  }


  private fun buildEngageAppComponent() : AppComponent {
    return DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .build()
  }

  fun getComponent(): AppComponent {
    return appComponent
  }
}