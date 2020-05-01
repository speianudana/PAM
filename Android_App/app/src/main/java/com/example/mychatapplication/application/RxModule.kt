package com.example.mychatapplication.application

import com.example.mychatapplication.util.RxSchedulers
import dagger.Module
import dagger.Provides

@Module
class RxModule {
  @Provides
  @AppScope
  fun rxSchedulers(): RxSchedulers {
    return RxSchedulers()
  }
}