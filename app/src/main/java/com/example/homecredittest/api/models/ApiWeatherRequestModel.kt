package com.example.homecredittest.api.models

data class ApiWeatherRequestModel(
    var city: String,
    var units: String,
    var appid: String
)