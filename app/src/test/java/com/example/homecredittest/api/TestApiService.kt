package com.example.homecredittest.api

import com.example.homecredittest.api.models.WeatherResponseListModel
import com.example.homecredittest.api.models.WeatherResponseModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit

interface TestApiService {
    @GET("data/2.5/group")
    suspend fun getGroup(@QueryMap params: HashMap<String, Any>): WeatherResponseModel

    @GET("data/2.5/weather")
    suspend fun getWeather(@QueryMap params: HashMap<String, Any>): WeatherResponseListModel

    companion object {
        fun create(baseUrl: String): TestApiService {

            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.readTimeout(5000, TimeUnit.MILLISECONDS)
            okHttpClient.connectTimeout(5000, TimeUnit.MILLISECONDS)
            okHttpClient.build()
            val client: OkHttpClient = okHttpClient.build()

            val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(TestApiService::class.java)
        }
    }
}