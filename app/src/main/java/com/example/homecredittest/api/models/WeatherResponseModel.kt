package com.example.homecredittest.api.models

import com.google.gson.annotations.SerializedName

data class WeatherResponseModel(
    @SerializedName("cnt")
    var count: Int,
    @SerializedName("list")
    var weatherResponseList: List<WeatherResponseListModel>
)

data class WeatherResponseListModel(
    @SerializedName("name")
    var city: String,
    @SerializedName("main")
    var tempWeatherModel: Temperature,
    @SerializedName("weather")
    var weatherMain: List<WeatherMain>
)

data class Temperature(
    @SerializedName("temp")
    var temp: Double,
    @SerializedName("temp_min")
    var low: Double,
    @SerializedName("temp_max")
    var high: Double
)

data class WeatherMain(
    @SerializedName("main")
    var main: String,
    @SerializedName("description")
    var description: String
)