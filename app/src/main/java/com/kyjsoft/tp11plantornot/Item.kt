package com.kyjsoft.tp11plantornot

data class PlantName(
    var cntntsSj : String
)

// 기상청
data class WeatherResponse(val response: Weather)
data class Weather(val body: Body)
data class Body(val dataType : String, val items : Items, val totalCount : Int)
data class Items(val item : MutableList<Item>)

data class Item(
    var category : String,
    var fcstValue : String
)



// 병충해












