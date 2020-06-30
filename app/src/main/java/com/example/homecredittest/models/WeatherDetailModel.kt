package com.example.homecredittest.models

data class WeatherDetailModel(
    var key: Int,
    var fav: Boolean,
    var city: String,
    var temp: String,
    var state: String,
    var highTemp: String,
    var lowTemp: String
)