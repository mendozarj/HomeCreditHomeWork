package com.example.homecredittest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.homecredittest.R
import com.example.homecredittest.activities.MainActivity
import com.example.homecredittest.api.models.ApiGroupRequestModel
import com.example.homecredittest.api.models.WeatherResponseModel
import com.example.homecredittest.databinding.FragmentFirstBinding
import com.example.homecredittest.models.WeatherModel
import com.example.homecredittest.viewmodels.GroupViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val groupViewModel: GroupViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.parentLinear.visibility = View.GONE
        setFavorites()

        groupViewModel.getGroup(ApiGroupRequestModel("1701668,3067696,1835848","metric","a0d31d9a6674d186a5385a07187717f6"))

        groupViewModel.errorNetwork.observe(viewLifecycleOwner, Observer { errorMsg ->
            if(binding.refresh.isRefreshing)
                binding.refresh.isRefreshing = false

            binding.parentLinear.visibility = View.GONE
            binding.myProgress.visibility = View.GONE
            Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG).show()
        })

        groupViewModel.groupOfWeatherResponse.observe(viewLifecycleOwner, Observer { weather ->
            if(binding.refresh.isRefreshing)
                binding.refresh.isRefreshing = false

            binding.parentLinear.visibility = View.VISIBLE
            binding.myProgress.visibility = View.GONE

            weather?.let { weatherModel ->
                setBackgrounds(weatherModel)
                binding.weather = WeatherModel(
                    weatherModel.weatherResponseList[0].city,
                    weatherModel.weatherResponseList[1].city,
                    weatherModel.weatherResponseList[2].city,
                    weatherModel.weatherResponseList[0].tempWeatherModel.temp.toBigDecimal().setScale(1, RoundingMode.HALF_UP).toString()+"\u2103",
                    weatherModel.weatherResponseList[1].tempWeatherModel.temp.toBigDecimal().setScale(1, RoundingMode.HALF_UP).toString()+"\u2103",
                    weatherModel.weatherResponseList[2].tempWeatherModel.temp.toBigDecimal().setScale(1, RoundingMode.HALF_UP).toString()+"\u2103",
                    weatherModel.weatherResponseList[0].weatherMain[0].main,
                    weatherModel.weatherResponseList[1].weatherMain[0].main,
                    weatherModel.weatherResponseList[2].weatherMain[0].main
                )
            }

            navigateDetails(weather)
        })

        binding.refresh.setOnRefreshListener {
            groupViewModel.getGroup(ApiGroupRequestModel("1701668,3067696,1835848","metric","a0d31d9a6674d186a5385a07187717f6"))
        }
    }

    private fun setBackgrounds(
        weatherModel: WeatherResponseModel
    ) {
        if(weatherModel.weatherResponseList[0].tempWeatherModel.temp in 15.5..30.0) binding.manila.background = resources.getDrawable(R.drawable.round_warm)
        if(weatherModel.weatherResponseList[0].tempWeatherModel.temp in 0.0..15.4) binding.manila.background = resources.getDrawable(R.drawable.round_cool)
        if(weatherModel.weatherResponseList[0].tempWeatherModel.temp <= 0) binding.manila.background = resources.getDrawable(R.drawable.round_freeze)
        if(weatherModel.weatherResponseList[0].tempWeatherModel.temp > 30) binding.manila.background = resources.getDrawable(R.drawable.round_hot)


        if(weatherModel.weatherResponseList[1].tempWeatherModel.temp in 15.5..30.0) binding.prague.background = resources.getDrawable(R.drawable.round_warm)
        if(weatherModel.weatherResponseList[1].tempWeatherModel.temp in 0.0..15.4) binding.prague.background = resources.getDrawable(R.drawable.round_cool)
        if(weatherModel.weatherResponseList[1].tempWeatherModel.temp <= 0) binding.prague.background = resources.getDrawable(R.drawable.round_freeze)
        if(weatherModel.weatherResponseList[1].tempWeatherModel.temp > 30) binding.prague.background = resources.getDrawable(R.drawable.round_hot)

        if(weatherModel.weatherResponseList[2].tempWeatherModel.temp in 15.5..30.0) binding.seoul.background = resources.getDrawable(R.drawable.round_warm)
        if(weatherModel.weatherResponseList[2].tempWeatherModel.temp in 0.0..15.4) binding.seoul.background = resources.getDrawable(R.drawable.round_cool)
        if(weatherModel.weatherResponseList[2].tempWeatherModel.temp <= 0) binding.seoul.background = resources.getDrawable(R.drawable.round_freeze)
        if(weatherModel.weatherResponseList[2].tempWeatherModel.temp > 30) binding.seoul.background = resources.getDrawable(R.drawable.round_hot)
    }

    private fun setFavorites() {
        when{
            (requireActivity() as MainActivity).manilaFav -> binding.manilaFav.visibility = View.VISIBLE
            else -> binding.manilaFav.visibility = View.INVISIBLE
        }

        when{
            (requireActivity() as MainActivity).pragueFav -> binding.pragueFav.visibility = View.VISIBLE
            else -> binding.pragueFav.visibility = View.INVISIBLE
        }

        when{
            (requireActivity() as MainActivity).seoulFav -> binding.seoulFav.visibility = View.VISIBLE
            else -> binding.seoulFav.visibility = View.INVISIBLE
        }
    }

    private fun navigateDetails(weatherModel: WeatherResponseModel) {
        binding.manila.setOnClickListener {
            val bundle: Bundle = bundleOf(
                "key"   to 0,
                "fav"   to (requireActivity() as MainActivity).manilaFav,
                "city"  to weatherModel.weatherResponseList[0].city)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

        binding.prague.setOnClickListener {
            val bundle: Bundle = bundleOf(
                "key"   to 1,
                "fav"   to (requireActivity() as MainActivity).pragueFav,
                "city"  to weatherModel.weatherResponseList[1].city)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

        binding.seoul.setOnClickListener {
            val bundle: Bundle = bundleOf(
                "key"   to 2,
                "fav"   to (requireActivity() as MainActivity).seoulFav,
                "city"  to weatherModel.weatherResponseList[2].city)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
    }
}