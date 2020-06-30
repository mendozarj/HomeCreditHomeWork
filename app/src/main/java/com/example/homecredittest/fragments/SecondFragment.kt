package com.example.homecredittest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.homecredittest.R
import com.example.homecredittest.activities.MainActivity
import com.example.homecredittest.api.models.ApiWeatherRequestModel
import com.example.homecredittest.databinding.FragmentSecondBinding
import com.example.homecredittest.models.WeatherDetailModel
import com.example.homecredittest.viewmodels.DetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    @Inject lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.parentLinear.visibility = View.GONE

        detailViewModel.getWeather(ApiWeatherRequestModel(arguments?.getString("city")!!,"metric","a0d31d9a6674d186a5385a07187717f6"))

        detailViewModel.errorNetwork.observe(viewLifecycleOwner, Observer { errorMsg ->
            if(binding.refresh.isRefreshing)
                binding.refresh.isRefreshing = false

            binding.parentLinear.visibility = View.GONE
            binding.myProgress.visibility = View.GONE
            Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG).show()
        })

        detailViewModel.weatherResponse.observe(viewLifecycleOwner, Observer { weatherResponse ->
            if(binding.refresh.isRefreshing)
                binding.refresh.isRefreshing = false

            binding.parentLinear.visibility = View.VISIBLE
            binding.myProgress.visibility = View.GONE

            weatherResponse?.let { weatherResponseModel ->
                binding.weatherDetails = WeatherDetailModel (
                    arguments?.getInt("key")!!,
                    arguments?.getBoolean("fav")!!,
                    arguments?.getString("city")!!,
                    weatherResponseModel.tempWeatherModel.temp.toBigDecimal().setScale(1, RoundingMode.HALF_UP).toString()+"\u2103",
                    weatherResponseModel.weatherMain[0].main,
                    weatherResponse.tempWeatherModel.high.toBigDecimal().setScale(0, RoundingMode.HALF_UP).toString()+"\u2103",
                    weatherResponse.tempWeatherModel.low.toBigDecimal().setScale(0, RoundingMode.HALF_UP).toString()+"\u2103"
                )
            }
        })

        setFavorites()

        binding.fav.setOnClickListener {
            println("Key -> ${arguments?.getInt("key")!!}")
            when(arguments?.getInt("key")!!) {
                0 -> {
                    if((requireActivity() as MainActivity).manilaFav) {
                        (requireActivity() as MainActivity).manilaFav = false
                        binding.fav.setImageResource(R.drawable.ic_favorite_border_24)
                    }
                    else {
                        (requireActivity() as MainActivity).manilaFav = true
                        binding.fav.setImageResource(R.drawable.ic_favorite_24)
                    }
                }
                1 -> {
                    if((requireActivity() as MainActivity).pragueFav) {
                        (requireActivity() as MainActivity).pragueFav = false
                        binding.fav.setImageResource(R.drawable.ic_favorite_border_24)
                    }
                    else {
                        (requireActivity() as MainActivity).pragueFav = true
                        binding.fav.setImageResource(R.drawable.ic_favorite_24)
                    }
                }
                2 -> {
                    if((requireActivity() as MainActivity).seoulFav) {
                        (requireActivity() as MainActivity).seoulFav = false
                        binding.fav.setImageResource(R.drawable.ic_favorite_border_24)
                    }
                    else {
                        (requireActivity() as MainActivity).seoulFav = true
                        binding.fav.setImageResource(R.drawable.ic_favorite_24)
                    }
                }
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.refresh.setOnRefreshListener {
            detailViewModel.getWeather(ApiWeatherRequestModel(arguments?.getString("city")!!,"metric","a0d31d9a6674d186a5385a07187717f6"))
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                }
            })
    }

    private fun setFavorites() {
        if(arguments?.getBoolean("fav")!!)
            binding.fav.setImageResource(R.drawable.ic_favorite_24)
        else
            binding.fav.setImageResource(R.drawable.ic_favorite_border_24)
    }
}