package com.group21.android.basketballcounter.api

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("main") lateinit var weather: WeatherMain
}