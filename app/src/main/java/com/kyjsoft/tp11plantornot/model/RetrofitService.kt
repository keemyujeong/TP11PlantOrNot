package com.kyjsoft.tp11plantornot.model

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



    // Dothome서버에 작성글 게시판
    @Multipart
    @POST("TPplantOrNot/insertBoardDB.php")
    fun postBoardDataToServer(@PartMap dataPart : Map<String, String>,
                              @Part filePart : MultipartBody.Part) : Call<String>

    @GET("TPplantOrNot/loadBoardDB.php")
    fun loadBoardDataFromServer() : Call<MutableList<BoardRecyclerItem>>


    // Dothome서버에 프로필 게시판
    @Multipart
    @POST("TPplantOrNot/insertProfileDB.php")
    fun postProfileDataToServer(@PartMap dataPart : Map<String, String>,
                              @Part filePart : MultipartBody.Part) : Call<String>

    @Multipart
    @POST("TPplantOrNot/loadProfileDB.php")
    fun loadProfileDataToServer(@PartMap datrpart : Map<String, String>) : Call<MutableList<ProfileItem>>


    // Dothome서버에 좋아요 DB
    @Multipart
    @POST("TPplantOrNot/insertLikeDB.php")
    fun postLikeDataFromServer(@PartMap dataPart: MutableMap<String, String>) : Call<String>

    @Multipart
    @POST("TPplantOrNot/deleteLikeDB.php")
    fun deleteLikeDataFromServer(@PartMap dataPart: MutableMap<String, String>) : Call<String>

    // Dothome서버에 내가 쓴 글 관리(제목, 글)
    @Multipart
    @POST("TPplantOrNot/loadMyBoardDB.php")
    fun loadMyPostDataFromServer(@PartMap dataPart: MutableMap<String, String>) : Call<MutableList<MyPostRecyclerItem>>

    @Multipart
    @POST("TPplantOrNot/deleteMyBoardDB.php")
    fun deleteMyPostDataFromServer(@PartMap dataPart: MutableMap<String, String>) : Call<String>


    // homefragment 농작업 일정 ( TODO 실패 )
    @GET("service/farmWorkingPlanNew/workScheduleDtl")
    fun farmDataToString(
        @Query("cntntsNo") cntntsNo : String,
        @Query("apiKey") apiKey : String
    ) : Call<String>




























    


}