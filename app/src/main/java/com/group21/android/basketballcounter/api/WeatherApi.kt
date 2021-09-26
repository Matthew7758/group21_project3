package com.group21.android.basketballcounter.api

import retrofit2.http.GET
import retrofit2.Call

interface WeatherApi {
    @GET(
        "data/2.5/" +
                "weather?q=Worcester,us" +
                "&APPID=070e9125904e852c760a223556296bd5"
    )
    fun fetchWeather(): Call<WeatherResponse>
}