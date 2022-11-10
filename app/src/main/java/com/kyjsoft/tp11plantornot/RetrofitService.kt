package com.kyjsoft.tp11plantornot

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.*

interface RetrofitService {

    // 기상청
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


    // 병충해
    @GET("serviceCode=SVC03&serviceType=AA001")
    fun getInsectData(
        @Query("apiKey") apiKey: String,
        @Query("cropName") cropName : String = "벼",
        @Query("sickNameKor") sickNameKor : String,
        ) : Call<Service>

    @GET("serviceCode=SVC03&serviceType=AA001")
    fun InsectDataToString(
        @Query("apiKey") apiKey: String,
        @Query("cropName") cropName : String = "벼",
        @Query("sickNameKor") sickNameKor : String,
        ) : Call<String>



    // 농작업일정
    @GET("service/farmWorkingPlanNew/workScheduleDt")
    fun getPlantData(
        @Query("cntntsNo") cntntsNo : Int,
        @Query("apiKey") apiKey : String,
        ) : Call<Response>

    @GET("service/farmWorkingPlanNew/workScheduleDt")
    fun PlantDataToString(
        @Query("cntntsNo") cntntsNo : Int,
        @Query("apiKey") apiKey : String,
        ) : Call<String>

















    


}