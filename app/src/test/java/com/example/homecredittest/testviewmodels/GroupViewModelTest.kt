package com.example.homecredittest.testviewmodels

import com.example.homecredittest.api.TestApiService
import com.example.homecredittest.api.models.WeatherResponseModel
import com.example.homecredittest.testrepo.TestWeatherRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GroupViewModelTest {

    private lateinit var testWeatherRepository: TestWeatherRepository
    private lateinit var api:TestApiService

    @Before
    fun setup() {
        api = TestApiService.create("https://api.openweathermap.org/")
        testWeatherRepository = TestWeatherRepository(api)
    }

    @Test
    fun testApiCityCall() = runBlocking {
        val response: WeatherResponseModel = testWeatherRepository.getGroup()
        println("[" +
            "City of: ${response.weatherResponseList[0].city}, " +
            "City of: ${response.weatherResponseList[1].city}, " +
            "City of: ${response.weatherResponseList[2].city}]"
        )

        assertEquals("Manila", response.weatherResponseList[0].city)
        assertEquals("Prague", response.weatherResponseList[1].city)
        assertEquals("Seoul", response.weatherResponseList[2].city)
    }

    @Test
    fun testApiTempCall() = runBlocking {
        val response: WeatherResponseModel = testWeatherRepository.getGroup()
        println("[" +
            "Manila Temp: ${response.weatherResponseList[0].tempWeatherModel.temp}℃, " +
            "Prague Temp: ${response.weatherResponseList[1].tempWeatherModel.temp}℃, " +
            "Seoul Temp: ${response.weatherResponseList[2].tempWeatherModel.temp}℃]"
        )

        assertNotNull("Something went wrong", response.weatherResponseList[0].tempWeatherModel.temp)
        assertNotNull("Something went wrong", response.weatherResponseList[1].tempWeatherModel.temp)
        assertNotNull("Something went wrong", response.weatherResponseList[2].tempWeatherModel.temp)
    }

    @Test
    fun testApiStateCall() = runBlocking {
        val response: WeatherResponseModel = testWeatherRepository.getGroup()

        println("[" +
            "Manila State: ${response.weatherResponseList[0].weatherMain[0].main}, " +
            "Prague State: ${response.weatherResponseList[1].weatherMain[0].main}, " +
            "Seoul State: ${response.weatherResponseList[2].weatherMain[0].main}]"
        )

        assertNotNull("Something went wrong", response.weatherResponseList[0].weatherMain[0].main)
        assertNotNull("Something went wrong", response.weatherResponseList[1].weatherMain[0].main)
        assertNotNull("Something went wrong", response.weatherResponseList[2].weatherMain[0].main)
    }
}