package com.kyjsoft.tp11plantornot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.kyjsoft.tp11plantornot.databinding.ActivityPickBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PickActivity : AppCompatActivity() {

    val binding : ActivityPickBinding by lazy { ActivityPickBinding.inflate(layoutInflater) }
    var items:MutableList<PickRecyclerItem> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.adapter = PickAdapter(this, items)



//        for(cntntsNo in 30595 until 30771){
//            loadData(cntntsNo)
//        }
        loadData(30700)

        //



    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    fun loadData(cntntsNo : Int) {
        // TODO 더미데이터
        items.add(0,PickRecyclerItem("제발 떠라"))

        val baseUrl = "http://api.nongsaro.go.kr/"
        val apiKey = "20221021WSJM62P0MYCVEVLK5V3FA"
        val retrofit : Retrofit = RetrofitHelper.getInstance(baseUrl)
//        retrofit.create(RetrofitService::class.java).getPlantData(
//            cntntsNo,
//            apiKey,
//        ).enqueue(object : Callback<Response> {
//            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
//
//                val result: Response? = response.body()
//                result?.body?.item?.let {
//                    items.add(PickRecyclerItem(it.kidofcomdtySeCodeNm))
//                    binding.recyclerView.adapter?.notifyDataSetChanged()
//
//                }
//
//            }
//
//            override fun onFailure(call: Call<Response>, t: Throwable) {
//                AlertDialog.Builder(this@PickActivity).setMessage("실패 : ${t.message}").show()
//            }
//
//        })

        // scalars
        retrofit.create(RetrofitService::class.java).PlantDataToString(
            cntntsNo,
            apiKey
        ).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("TAG-P", response.body().toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("TAG-P", t.message.toString())
                items.add(PickRecyclerItem("제발 떠라"))
            }

        })
    }
}