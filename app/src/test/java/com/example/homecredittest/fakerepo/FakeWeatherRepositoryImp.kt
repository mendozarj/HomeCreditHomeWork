package com.example.homecredittest.fakerepo

import com.example.homecredittest.api.models.*

class FakeWeatherRepositoryImp {

    private val list:List<WeatherResponseListModel>  = listOf(
        WeatherResponseListModel(
            "Manila",
            Temperature(26.7, 19.5, 26.0),
            listOf(WeatherMain("Cloud","Cloudy fuzzy"))
        ),
        WeatherResponseListModel(
            "Prague",
            Temperature(15.7, 11.5, 20.0),
            listOf(WeatherMain("Clear","Clear sky"))
        ),
        WeatherResponseListModel(
            "Seoul",
            Temperature(21.7, 18.5, 25.5),
            listOf(WeatherMain("Rain","Rainy static"))
        )
    )

    private val weatherList: List<WeatherResponseListModel> = listOf(
        WeatherResponseListModel(
            "Manila",
            Temperature(26.7, 19.5, 26.0),
            listOf(WeatherMain("Cloudy","Cloudy fuzzy"))
        )
    )

    fun getGroup(): List<WeatherResponseListModel>      = list

    fun getWeather(): List<WeatherResponseListModel>    = weatherList
}