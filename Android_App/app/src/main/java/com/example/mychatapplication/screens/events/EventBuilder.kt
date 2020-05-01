package com.example.mychatapplication.screens.events

import androidx.fragment.app.FragmentActivity
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.application.AppComponent
import com.example.mychatapplication.screens.events.mvp.EventModel
import com.example.mychatapplication.screens.events.mvp.EventPresenter
import com.example.mychatapplication.screens.events.mvp.EventView
import com.example.mychatapplication.storage.AppStorage
import com.example.mychatapplication.util.RxSchedulers
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class EventScope

@EventScope
@Component(modules = [EventModule::class], dependencies = [AppComponent::class])
interface EventComponent {
  fun inject(fragment: EventsFragment)
}

@Module
class EventModule(private val fragmentActivity: FragmentActivity) {

  @EventScope
  @Provides
  fun view() = EventView(fragmentActivity)

  @EventScope
  @Provides
  fun model(appStorage: AppStorage) = EventModel(fragmentActivity, appStorage)

  @EventScope
  @Provides
  fun presenter(view: EventView, model: EventModel, rxSchedulers: RxSchedulers, serverRequestService: ServerRequestService) = EventPresenter(model, view, rxSchedulers, serverRequestService)
}