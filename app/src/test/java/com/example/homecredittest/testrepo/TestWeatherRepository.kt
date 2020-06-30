package com.example.homecredittest.testrepo

import com.example.homecredittest.api.TestApiService
import com.example.homecredittest.api.models.*

class TestWeatherRepository (
    private val apiService: TestApiService
) {

    suspend fun getGroup(): WeatherResponseModel {
        val params = HashMap<String, Any>()
        params["id"]    = "1701668,3067696,1835848"
        params["units"] = "metric"
        params["appid"] = "a0d31d9a6674d186a5385a07187717f6"

        return  apiService.getGroup(params)
    }

    suspend fun getWeather(): WeatherResponseListModel {
        val params = HashMap<String, Any>()
        params["q"]     = "Manila"
        params["units"] = "metric"
        params["appid"] = "a0d31d9a6674d186a5385a07187717f6"
        return apiService.getWeather(params)
    }
}