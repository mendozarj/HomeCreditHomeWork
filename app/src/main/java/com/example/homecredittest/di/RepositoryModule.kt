package com.example.homecredittest.di

import com.example.homecredittest.repository.WeatherRepository
import com.example.homecredittest.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Qualifier

@Qualifier
annotation class ApiRepo

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {

    @ApiRepo
    @Binds
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}