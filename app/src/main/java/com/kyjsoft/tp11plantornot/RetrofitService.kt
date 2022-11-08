package com.kyjsoft.tp11plantornot

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface RetrofitService {

    @GET("s/getVilageFcst")
    fun getWeatherInfo(
        @Query("serviceKey") apiKey: String,
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
        @Query("dataType") dataType: String,
        @Query("base_date") baseDate : String,
        @Query("base_time") base_time: String,
        @Query("nx") nx: Int,
        @Query("ny") ny: Int,

        ) : Call<Weather>



//
//    @GET("/service/farmWorkingPlanNew/workScheduleDt?apiKey=20221021WSJM62P0MYCVEVLK5V3FA&cntntsNo={contentsNum}")
//    fun getPlantData(@Path("contentsNum") contentsNum: Int) : Call<String>
    // 위에 있는 건 기상청 json 파싱할 때 쓰셈





    


}