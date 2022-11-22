package com.kyjsoft.tp11plantornot

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
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

        binding.search.setOnClickListener {
            // TODO 검색 필터 기능 쓸거야? -> searchview라는 게 있던데
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        }



//        for(cntntsNo in 30595 until 30771){
//            loadData(cntntsNo)
//        }
        loadData(30700)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    fun loadData(cntntsNo : Int) {
        // TODO xml 농작업 일정 파싱
        items.add(0,PickRecyclerItem("제발 떠라"))

        val baseUrl = "http://api.nongsaro.go.kr/"
        val apiKey = "20221021WSJM62P0MYCVEVLK5V3FA"

    }
}