package com.example.homecredittest.fakeviewmodels

import com.example.homecredittest.fakerepo.FakeWeatherRepositoryImp
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var fakeWeatherRepositoryImp: FakeWeatherRepositoryImp

    @Before
    fun setup() {
        fakeWeatherRepositoryImp = FakeWeatherRepositoryImp()
    }

    @Test
    fun getWeatherResponse() {
        println("Asserting City as Manila    --> ${fakeWeatherRepositoryImp.getWeather()[0].city} pass")
        println("Asserting Temp as 26.7      --> ${fakeWeatherRepositoryImp.getWeather()[0].tempWeatherModel.temp} pass")
        println("Asserting Low Temp as 19.5  --> ${fakeWeatherRepositoryImp.getWeather()[0].tempWeatherModel.low} pass")
        println("Asserting High Temp as 26.0 --> ${fakeWeatherRepositoryImp.getWeather()[0].tempWeatherModel.high} pass")

        assertEquals("Manila", fakeWeatherRepositoryImp.getWeather()[0].city)
        assertEquals("26.0", fakeWeatherRepositoryImp.getWeather()[0].tempWeatherModel.high.toString())
        assertEquals("19.5", fakeWeatherRepositoryImp.getWeather()[0].tempWeatherModel.low.toString())
        assertEquals("26.7", fakeWeatherRepositoryImp.getWeather()[0].tempWeatherModel.temp.toString())
    }
}