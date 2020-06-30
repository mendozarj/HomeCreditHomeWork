package com.example.homecredittest.repository

import com.example.homecredittest.api.interfaces.ApiHelper
import com.example.homecredittest.api.models.ApiGroupRequestModel
import com.example.homecredittest.api.models.ApiWeatherRequestModel
import com.example.homecredittest.api.models.WeatherResponseListModel
import com.example.homecredittest.api.models.WeatherResponseModel
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
): WeatherRepository  {

    override suspend fun getGroup(apiGroupRequestModel: ApiGroupRequestModel): WeatherResponseModel = apiHelper.getGroup(apiGroupRequestModel)

    override suspend fun getWeather(apiWeatherRequestModel: ApiWeatherRequestModel): WeatherResponseListModel = apiHelper.getWeather(apiWeatherRequestModel)
}