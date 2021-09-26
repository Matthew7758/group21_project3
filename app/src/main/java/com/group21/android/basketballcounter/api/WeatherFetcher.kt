package com.group21.android.basketballcounter.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "WeatherFetcher"

class WeatherFetcher {

    private val weatherApi: WeatherApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weatherApi = retrofit.create(WeatherApi::class.java)
    }

    fun fetchWeather(): LiveData<WeatherItem> {
        val responseLiveData: MutableLiveData<WeatherItem> = MutableLiveData()
        val weatherRequest: Call<WeatherResponse> = weatherApi.fetchWeather()

        weatherRequest.enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch weather data.", t)
            }
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                val weatherResponse: WeatherResponse? = response.body()
                val weatherMain: WeatherMain? = weatherResponse?.weather
                val weatherItem = weatherMain?.let { WeatherItem(weatherResponse.city, it.temp) }
                responseLiveData.value = weatherItem
            }
        })
        return responseLiveData
    }

}