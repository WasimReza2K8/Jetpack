package com.codeartist.androidpractice.di

import com.codeartist.androidpractice.network.RequestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {
    @Singleton
    @Provides
    fun provideBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideRequestAPI(retrofit: Retrofit.Builder): RequestApi {
        return retrofit
                .build()
                .create(RequestApi::class.java)
    }

    companion object {
        const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"
    }
}