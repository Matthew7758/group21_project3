package com.group21.android.basketballcounter.api

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface WeatherApi {
    @GET(
        "data/2.5/weather?"
    )
    fun fetchWeather(@Query("q") cityName: String, @Query("APPID") apikey: String): Call<WeatherResponse>
}


