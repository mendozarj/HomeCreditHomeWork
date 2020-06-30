package com.example.homecredittest.api.interfaces

import com.example.homecredittest.api.models.WeatherResponseModel
import com.example.homecredittest.api.models.ApiGroupRequestModel
import com.example.homecredittest.api.models.ApiWeatherRequestModel
import com.example.homecredittest.api.models.WeatherResponseListModel

interface ApiHelper {
    suspend fun getGroup(apiGroupRequestModel: ApiGroupRequestModel): WeatherResponseModel
    suspend fun getWeather(apiWeatherRequestModel: ApiWeatherRequestModel): WeatherResponseListModel
}