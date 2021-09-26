package com.group21.android.basketballcounter.api

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("name") lateinit var city: String
    @SerializedName("main") lateinit var weather: WeatherMain
}