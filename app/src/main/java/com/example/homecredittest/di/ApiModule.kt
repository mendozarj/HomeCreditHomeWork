package com.example.homecredittest.di

import com.example.homecredittest.api.helper.ApiHelperImpl
import com.example.homecredittest.api.interfaces.ApiHelper
import com.example.homecredittest.api.interfaces.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun providesWeatherApi(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.readTimeout(10, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(10, TimeUnit.SECONDS)
        okHttpClient.build()

        val client: OkHttpClient = okHttpClient.build()

        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesApiHelper(impl: ApiHelperImpl): ApiHelper = impl
}