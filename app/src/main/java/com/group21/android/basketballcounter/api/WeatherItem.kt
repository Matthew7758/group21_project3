package com.group21.android.basketballcounter.api

// the point of this class is to store the data we use as a data class. Since the two items we fetch are on different
// hierarchies, i created a separate class.
data class WeatherItem (

    var city: String = "",
    var temp: Double = 0.0 // in Kelvin, will have to translate via a helper function
)