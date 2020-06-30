package com.example.homecredittest.fakeviewmodels

import com.example.homecredittest.fakerepo.FakeWeatherRepositoryImp
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GroupViewModelTest {

    private lateinit var fakeWeatherRepositoryImp: FakeWeatherRepositoryImp

    @Before
    fun setup() {
        fakeWeatherRepositoryImp = FakeWeatherRepositoryImp()
    }

    @Test
    fun getGroupOfWeatherResponse() {
        println("Asserting City as Manila          --> ${fakeWeatherRepositoryImp.getGroup()[0].city} pass")
        println("Asserting City as Prague          --> ${fakeWeatherRepositoryImp.getGroup()[1].city} pass")
        println("Asserting City as Seoul           --> ${fakeWeatherRepositoryImp.getGroup()[2].city} pass")

        println("Asserting Manila Temp as 26.7     --> ${fakeWeatherRepositoryImp.getGroup()[0].tempWeatherModel.temp} pass")
        println("Asserting Prague Temp as 15.7     --> ${fakeWeatherRepositoryImp.getGroup()[1].tempWeatherModel.temp} pass")
        println("Asserting Seoul Temp as 21.7      --> ${fakeWeatherRepositoryImp.getGroup()[2].tempWeatherModel.temp} pass")

        println("Asserting Manila State as Cloud   --> ${fakeWeatherRepositoryImp.getGroup()[0].weatherMain[0].main} pass")
        println("Asserting Prague State as Clear   --> ${fakeWeatherRepositoryImp.getGroup()[1].weatherMain[0].main} pass")
        println("Asserting Seoul State as Rain     --> ${fakeWeatherRepositoryImp.getGroup()[2].weatherMain[0].main} pass")

        assertEquals("Manila",  fakeWeatherRepositoryImp.getGroup()[0].city)
        assertEquals("Prague",  fakeWeatherRepositoryImp.getGroup()[1].city)
        assertEquals("Seoul",   fakeWeatherRepositoryImp.getGroup()[2].city)

        assertEquals("26.7",  fakeWeatherRepositoryImp.getGroup()[0].tempWeatherModel.temp.toString())
        assertEquals("15.7",  fakeWeatherRepositoryImp.getGroup()[1].tempWeatherModel.temp.toString())
        assertEquals("21.7",  fakeWeatherRepositoryImp.getGroup()[2].tempWeatherModel.temp.toString())

        assertEquals("Cloud",  fakeWeatherRepositoryImp.getGroup()[0].weatherMain[0].main)
        assertEquals("Clear",  fakeWeatherRepositoryImp.getGroup()[1].weatherMain[0].main)
        assertEquals("Rain",   fakeWeatherRepositoryImp.getGroup()[2].weatherMain[0].main)
    }
}