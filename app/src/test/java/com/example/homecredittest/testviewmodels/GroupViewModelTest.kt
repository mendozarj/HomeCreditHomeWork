package com.example.homecredittest.testviewmodels

import com.example.homecredittest.api.TestApiService
import com.example.homecredittest.api.models.WeatherResponseModel
import com.example.homecredittest.testrepo.TestWeatherRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GroupViewModelTest {

    private lateinit var testWeatherRepository: TestWeatherRepository
    private lateinit var groupResp: WeatherResponseModel
    private lateinit var api:TestApiService

    @Before
    fun setup() {
        api = TestApiService.create("https://api.openweathermap.org/")
        testWeatherRepository = TestWeatherRepository(api)
    }

    @Test
    fun testApiCityCall() = runBlocking {
        coroutineScope {
            groupResp = testWeatherRepository.getGroup()
        }
        println("[" +
            "City of: ${groupResp.weatherResponseList[0].city}, " +
            "City of: ${groupResp.weatherResponseList[1].city}, " +
            "City of: ${groupResp.weatherResponseList[2].city}]"
        )

        assertEquals("Manila", groupResp.weatherResponseList[0].city)
        assertEquals("Prague", groupResp.weatherResponseList[1].city)
        assertEquals("Seoul", groupResp.weatherResponseList[2].city)
    }

    @Test
    fun testApiTempCall() = runBlocking {
        coroutineScope {
            groupResp = testWeatherRepository.getGroup()
        }

        println("[" +
            "Manila Temp: ${groupResp.weatherResponseList[0].tempWeatherModel.temp}℃, " +
            "Prague Temp: ${groupResp.weatherResponseList[1].tempWeatherModel.temp}℃, " +
            "Seoul Temp: ${groupResp.weatherResponseList[2].tempWeatherModel.temp}℃]"
        )

        assertNotNull("Something went wrong", groupResp.weatherResponseList[0].tempWeatherModel.temp)
        assertNotNull("Something went wrong", groupResp.weatherResponseList[1].tempWeatherModel.temp)
        assertNotNull("Something went wrong", groupResp.weatherResponseList[2].tempWeatherModel.temp)
    }

    @Test
    fun testApiStateCall() = runBlocking {
        coroutineScope {
            groupResp = testWeatherRepository.getGroup()
        }

        println("[" +
            "Manila State: ${groupResp.weatherResponseList[0].weatherMain[0].main}, " +
            "Prague State: ${groupResp.weatherResponseList[1].weatherMain[0].main}, " +
            "Seoul State: ${groupResp.weatherResponseList[2].weatherMain[0].main}]"
        )

        assertNotNull("Something went wrong", groupResp.weatherResponseList[0].weatherMain[0].main)
        assertNotNull("Something went wrong", groupResp.weatherResponseList[1].weatherMain[0].main)
        assertNotNull("Something went wrong", groupResp.weatherResponseList[2].weatherMain[0].main)
    }
}