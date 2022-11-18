package com.kyjsoft.tp11plantornot

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*
import retrofit2.http.Part

interface RetrofitService {

    // 기상청
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

    // 지도 검색
    @GET("v2/local/search/address.json")
    fun getKakaoAddress(
        @Header("Authorization") key: String,
        @Query("query") query: String
    ) : Call<KakaoMapData>

    @GET("v2/local/search/address.json")
    fun getKakaoAddresstoString(
        @Header("Authorization") key: String,
        @Query("query") query: String
    ) : Call<String>




    // 병충해(TODO 실패! -> scalars로는 날아옴)
    @GET("service?serviceCode=SVC01&serviceType=AA001")
    fun getInsectData(
        @Query("apiKey") apiKey: String,
        @Query("cropName") cropName : String = G.plant,
        @Query("sickNameKor") sickNameKor : String,
        ) : Call<Service>

    @GET("service?serviceCode=SVC01&serviceType=AA001")
    fun insectDataToString(
        @Query("apiKey") apiKey: String,
        @Query("cropName") cropName : String = G.plant,
        @Query("sickNameKor") sickNameKor : String
        ) : Call<String>



    // 농작업일정( TODO 실패!)
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



    // Dothome서버에 작성글 게시판
    @Multipart
    @POST("TPplantOrNot/insertBoardDB.php")
    fun postBoardDataToServer(@PartMap dataPart : Map<String, String>,
                              @Part filePart : MultipartBody.Part) : Call<String>

    @GET("TPplantOrNot/loadBoardDB.php")
    fun loadBoardDataFromServer() : Call<MutableList<BoardDBItem>>


    // Dothome서버에 프로필 게시판
    @Multipart
    @POST("TPplantOrNot/insertProfileDB.php")
    fun postProfileDataToServer(@PartMap dataPart : Map<String, String>,
                              @Part filePart : MultipartBody.Part) : Call<String>

    @Multipart
    @POST("TPplantOrNot/loadProfileDB.php")
    fun loadProfileDataFromServer(@PartMap dataPart : Map<String, String>) : Call<ProfileDBItem>


    // Dothome서버에 좋아요 DB
    @Multipart
    @POST("TPplantOrNot/loadLikeDB.php")
    fun postLikeDataFromServer(@PartMap dataPart : Map<String, String>) : Call<String>



























    


}