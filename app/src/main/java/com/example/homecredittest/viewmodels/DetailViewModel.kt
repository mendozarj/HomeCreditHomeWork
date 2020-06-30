package com.example.homecredittest.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homecredittest.api.models.ApiWeatherRequestModel
import com.example.homecredittest.api.models.WeatherResponseListModel
import com.example.homecredittest.di.ApiRepo
import com.example.homecredittest.repository.WeatherRepository
import com.example.homecredittest.utils.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class DetailViewModel @ViewModelInject constructor(
    @ApiRepo private val weatherRepository: WeatherRepository,
    private val networkHelper: NetworkHelper
): ViewModel() {

    private var _weatherResponse = MutableLiveData<WeatherResponseListModel>()
    val weatherResponse: LiveData<WeatherResponseListModel>
        get() = _weatherResponse

    private var _errorNetwork = MutableLiveData<String>()
    val errorNetwork: LiveData<String>
        get() = _errorNetwork


    fun getWeather(apiWeatherRequestModel: ApiWeatherRequestModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (networkHelper.isNetworkConnected())
                    _weatherResponse.postValue(weatherRepository.getWeather(apiWeatherRequestModel))
                else _errorNetwork.postValue("Device might not be connected with Wifi/Data Internet Connection!")
            } catch (ignored: Exception) {
                _errorNetwork.postValue("Request Failed\nSomething went wrong!")
            }
        }
    }
}