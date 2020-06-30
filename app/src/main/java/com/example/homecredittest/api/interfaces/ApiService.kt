package com.example.homecredittest.api.interfaces

import com.example.homecredittest.api.models.WeatherResponseListModel
import com.example.homecredittest.api.models.WeatherResponseModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("data/2.5/group")
    suspend fun getGroup(@QueryMap params: HashMap<String, Any>): WeatherResponseModel

    @GET("data/2.5/weather")
    suspend fun getWeather(@QueryMap params: HashMap<String, Any>): WeatherResponseListModel

}