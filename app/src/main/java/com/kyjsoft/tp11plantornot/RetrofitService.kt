package com.kyjsoft.tp11plantornot

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.*

interface RetrofitService {

    @GET("getVilageFcst")
    fun weatherdataToString(
        @Query("serviceKey") apiKey: String,
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
        @Query("dataType") dataType: String,
        @Query("base_date") baseDate : String,
        @Query("base_time") base_time: String,
        @Query("nx") nx: Int,
        @Query("ny") ny: Int
    ) : Call<String>

    @GET("getVilageFcst")
    fun getWeatherInfo(
        @Query("serviceKey") apiKey: String,
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
        @Query("dataType") dataType: String,
        @Query("base_date") baseDate : String,
        @Query("base_time") base_time: String,
        @Query("nx") nx: Int,
        @Query("ny") ny: Int

        ) : Call<WeatherResponse>

    @GET("?serviceCode=SVC03&serviceType=AA001")
    fun bugDataToString(
        @Query("apiKey") apiKey: String,
        @Query("cropName") cropName: String,
        @Query("sickNameKor") sickNameKor: String,

    ) : Call<String>







    


}