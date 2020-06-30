package com.example.homecredittest.testviewmodels

import com.example.homecredittest.api.TestApiService
import com.example.homecredittest.api.models.WeatherResponseListModel
import com.example.homecredittest.api.models.WeatherResponseModel
import com.example.homecredittest.testrepo.TestWeatherRepositoryImp
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var testWeatherRepositoryImp: TestWeatherRepositoryImp
    private lateinit var api: TestApiService
    private val params = HashMap<String, Any>()

    @Before
    fun setup() {
        params["q"]     = "Manila"
        params["units"] = "metric"
        params["appid"] = "a0d31d9a6674d186a5385a07187717f6"
        api = TestApiService.create("https://api.openweathermap.org/")
        testWeatherRepositoryImp = TestWeatherRepositoryImp()
    }

    @Test
    fun testApiManilaDetails() = runBlocking {
        val response: WeatherResponseListModel = api.getWeather(params)
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

    @Test
    fun getWeatherResponse() {
        println("Asserting City as Manila    --> ${testWeatherRepositoryImp.getWeather()[0].city} pass")
        println("Asserting Temp as 26.7      --> ${testWeatherRepositoryImp.getWeather()[0].tempWeatherModel.temp} pass")
        println("Asserting Low Temp as 19.5  --> ${testWeatherRepositoryImp.getWeather()[0].tempWeatherModel.low} pass")
        println("Asserting High Temp as 26.0 --> ${testWeatherRepositoryImp.getWeather()[0].tempWeatherModel.high} pass")

        assertEquals("Manila", testWeatherRepositoryImp.getWeather()[0].city)
        assertEquals("26.0", testWeatherRepositoryImp.getWeather()[0].tempWeatherModel.high.toString())
        assertEquals("19.5", testWeatherRepositoryImp.getWeather()[0].tempWeatherModel.low.toString())
        assertEquals("26.7", testWeatherRepositoryImp.getWeather()[0].tempWeatherModel.temp.toString())
    }
}