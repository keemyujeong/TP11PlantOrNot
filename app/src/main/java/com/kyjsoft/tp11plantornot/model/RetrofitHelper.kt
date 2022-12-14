package com.kyjsoft.tp11plantornot.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitHelper {

    fun getInstance(baseurl: String) : Retrofit{

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit

    }



}