package com.kyjsoft.tp11plantornot

data class PlantName(
    var cntntsSj : String
)

// 기상청
data class Weather(val response: Response)
data class Response(val body: Body)
data class Body(val items : Items)
data class Items(val item : List<Item>)

data class Item(
    var category : String,
    var fcstValue : String
)


