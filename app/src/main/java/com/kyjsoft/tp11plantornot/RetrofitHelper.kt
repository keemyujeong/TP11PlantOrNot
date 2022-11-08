package com.kyjsoft.tp11plantornot

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitHelper {

    fun getInstance(baseurl: String) : Retrofit{

        var retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit

    }



}