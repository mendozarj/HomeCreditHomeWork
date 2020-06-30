package com.example.homecredittest.testviewmodels

import com.example.homecredittest.api.TestApiService
import com.example.homecredittest.api.models.WeatherResponseModel
import com.example.homecredittest.testrepo.TestWeatherRepositoryImp
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GroupViewModelTest {

    private lateinit var testWeatherRepositoryImp: TestWeatherRepositoryImp
    private lateinit var api:TestApiService
    private val params = HashMap<String, Any>()

    @Before
    fun setup() {
        params["id"]    = "1701668,3067696,1835848"
        params["units"] = "metric"
        params["appid"] = "a0d31d9a6674d186a5385a07187717f6"

        api = TestApiService.create("https://api.openweathermap.org/")
        testWeatherRepositoryImp = TestWeatherRepositoryImp()
    }

    @Test
    fun testApiCityCall() = runBlocking {
        val response: WeatherResponseModel = api.getGroup(params)
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
        val response: WeatherResponseModel = api.getGroup(params)
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
        val response: WeatherResponseModel = api.getGroup(params)

        println("[" +
            "Manila State: ${response.weatherResponseList[0].weatherMain[0].main}, " +
            "Prague State: ${response.weatherResponseList[1].weatherMain[0].main}, " +
            "Seoul State: ${response.weatherResponseList[2].weatherMain[0].main}]"
        )

        assertNotNull("Something went wrong", response.weatherResponseList[0].weatherMain[0].main)
        assertNotNull("Something went wrong", response.weatherResponseList[1].weatherMain[0].main)
        assertNotNull("Something went wrong", response.weatherResponseList[2].weatherMain[0].main)
    }

    @Test
    fun testWeatherRepository() {
        println("Asserting City as Manila          --> ${testWeatherRepositoryImp.getGroup()[0].city} pass")
        println("Asserting City as Prague          --> ${testWeatherRepositoryImp.getGroup()[1].city} pass")
        println("Asserting City as Seoul           --> ${testWeatherRepositoryImp.getGroup()[2].city} pass")

        println("Asserting Manila Temp as 26.7     --> ${testWeatherRepositoryImp.getGroup()[0].tempWeatherModel.temp} pass")
        println("Asserting Prague Temp as 15.7     --> ${testWeatherRepositoryImp.getGroup()[1].tempWeatherModel.temp} pass")
        println("Asserting Seoul Temp as 21.7      --> ${testWeatherRepositoryImp.getGroup()[2].tempWeatherModel.temp} pass")

        println("Asserting Manila State as Cloud   --> ${testWeatherRepositoryImp.getGroup()[0].weatherMain[0].main} pass")
        println("Asserting Prague State as Clear   --> ${testWeatherRepositoryImp.getGroup()[1].weatherMain[0].main} pass")
        println("Asserting Seoul State as Rain     --> ${testWeatherRepositoryImp.getGroup()[2].weatherMain[0].main} pass")

        assertEquals("Manila",  testWeatherRepositoryImp.getGroup()[0].city)
        assertEquals("Prague",  testWeatherRepositoryImp.getGroup()[1].city)
        assertEquals("Seoul",   testWeatherRepositoryImp.getGroup()[2].city)

        assertEquals("26.7",  testWeatherRepositoryImp.getGroup()[0].tempWeatherModel.temp.toString())
        assertEquals("15.7",  testWeatherRepositoryImp.getGroup()[1].tempWeatherModel.temp.toString())
        assertEquals("21.7",  testWeatherRepositoryImp.getGroup()[2].tempWeatherModel.temp.toString())

        assertEquals("Cloud",  testWeatherRepositoryImp.getGroup()[0].weatherMain[0].main)
        assertEquals("Clear",  testWeatherRepositoryImp.getGroup()[1].weatherMain[0].main)
        assertEquals("Rain",   testWeatherRepositoryImp.getGroup()[2].weatherMain[0].main)
    }
}