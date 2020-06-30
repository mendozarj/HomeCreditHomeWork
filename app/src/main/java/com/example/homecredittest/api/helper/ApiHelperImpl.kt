package com.example.homecredittest.api.helper

import com.example.homecredittest.api.interfaces.ApiHelper
import com.example.homecredittest.api.interfaces.ApiService
import com.example.homecredittest.api.models.ApiGroupRequestModel
import com.example.homecredittest.api.models.ApiWeatherRequestModel
import com.example.homecredittest.api.models.WeatherResponseListModel
import com.example.homecredittest.api.models.WeatherResponseModel
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService): ApiHelper {

    override suspend fun getGroup(apiGroupRequestModel: ApiGroupRequestModel): WeatherResponseModel {
        val params = HashMap<String, Any>()
        params["id"]    = apiGroupRequestModel.id
        params["units"] = apiGroupRequestModel.units
        params["appid"] = apiGroupRequestModel.appid

        return apiService.getGroup(params)
    }

    override suspend fun getWeather(apiWeatherRequestModel: ApiWeatherRequestModel): WeatherResponseListModel {
        val params = HashMap<String, Any>()
        params["q"]     = apiWeatherRequestModel.city
        params["units"] = apiWeatherRequestModel.units
        params["appid"] = apiWeatherRequestModel.appid

        return apiService.getWeather(params)
    }

}