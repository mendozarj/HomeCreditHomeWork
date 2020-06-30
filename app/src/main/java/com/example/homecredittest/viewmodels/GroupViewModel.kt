package com.example.homecredittest.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homecredittest.api.models.ApiGroupRequestModel
import com.example.homecredittest.api.models.WeatherResponseModel
import com.example.homecredittest.di.ApiRepo
import com.example.homecredittest.repository.WeatherRepository
import com.example.homecredittest.utils.NetworkHelper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class GroupViewModel @ViewModelInject constructor(
    @ApiRepo private val weatherRepository: WeatherRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private var _groupOfWeather = MutableLiveData<WeatherResponseModel>()
    val groupOfWeatherResponse: LiveData<WeatherResponseModel>
        get() = _groupOfWeather


    private var _errorNetwork = MutableLiveData<String>()
    val errorNetwork: LiveData<String>
        get() = _errorNetwork

    fun getGroup(apiGroupRequestModel: ApiGroupRequestModel) {
        viewModelScope.launch(IO) {
            try {
                if(networkHelper.isNetworkConnected())
                    _groupOfWeather.postValue(weatherRepository.getGroup(apiGroupRequestModel))
                else _errorNetwork.postValue("Device might not be connected with Wifi/Data Internet Connection!")
            } catch (ignored: Exception) {
                _errorNetwork.postValue("Request Failed\nSomething went wrong!")
            }
        }
    }



}