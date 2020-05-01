package com.example.mychatapplication.screens.itemInfo

import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.application.AppComponent
import com.example.mychatapplication.screens.itemInfo.mvp.ItemInfoModel
import com.example.mychatapplication.screens.itemInfo.mvp.ItemInfoPresenter
import com.example.mychatapplication.screens.itemInfo.mvp.ItemInfoView
import com.example.mychatapplication.storage.AppStorage
import com.example.mychatapplication.util.RxSchedulers
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class ItemInfoScope

@ItemInfoScope
@Component(modules = [ItemInfoModule::class], dependencies = [AppComponent::class])
interface ItemInfoComponent {

  fun inject(activity: ItemInfoActivity)
}

@Module
class ItemInfoModule(private val activity: AppCompatActivity) {

  @ItemInfoScope
  @Provides
  fun view() = ItemInfoView(activity)

  @ItemInfoScope
  @Provides
  fun model(appStorage: AppStorage) = ItemInfoModel(activity, appStorage)

  @ItemInfoScope
  @Provides
  fun presenter(view: ItemInfoView, model: ItemInfoModel, rxSchedulers: RxSchedulers, serverRequestService: ServerRequestService) = ItemInfoPresenter(model, view, rxSchedulers, serverRequestService)
}