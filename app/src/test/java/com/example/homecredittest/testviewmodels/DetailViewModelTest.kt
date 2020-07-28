package com.example.homecredittest.testviewmodels

import com.example.homecredittest.api.TestApiService
import com.example.homecredittest.api.models.WeatherResponseListModel
import com.example.homecredittest.api.models.WeatherResponseModel
import com.example.homecredittest.testrepo.TestWeatherRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var testWeatherRepository: TestWeatherRepository
    private lateinit var api: TestApiService
    private val params = HashMap<String, Any>()
    private lateinit var response: WeatherResponseListModel

    @Before
    fun setup() {
        api = TestApiService.create("https://api.openweathermap.org/")
        testWeatherRepository = TestWeatherRepository(api)
    }

    @Test
    fun testApiManilaDetails() = runBlocking {
        coroutineScope {
            response = testWeatherRepository.getWeather()
        }

        println("[" +
                "City: ${response.city}, " +
                "Temp: ${response.tempWeatherModel.temp}℃, " +
                "High: ${response.tempWeatherModel.high}℃, " +
                "Low: ${response.tempWeatherModel.low}℃, " +
                "State: ${response.weatherMain[0].main}]"
        )

        assertEquals("Manila", response.city)
        assertNotNull(response.tempWeatherModel.temp)
        assertNotNull(response.tempWeatherModel.high)
        assertNotNull(response.tempWeatherModel.low)
        assertNotNull(response.weatherMain[0].main)
    }
}