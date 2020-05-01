package com.example.mychatapplication.application

import android.content.Context
import com.example.mychatapplication.storage.AppStorage
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    @AppScope
    fun getAppStorage(context: Context): AppStorage {
        return AppStorage(context)
    }
}
