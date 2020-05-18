package com.example.mychatapplication.application

import com.example.mychatapplication.network.ServerRequestService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    companion object {
        const val BASE_URL = "https://91f71c69.ngrok.io"
    }

    @Provides
    @AppScope
    fun getRetrofitInstance(): ServerRequestService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(
            ServerRequestService::class.java
        )
    }
}
