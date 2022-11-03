package com.kyjsoft.tp11plantornot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kyjsoft.tp11plantornot.databinding.ActivityMyPostBinding

class MyPostActivity : AppCompatActivity() {

    var items : MutableList<MyPostRecyclerItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMyPostBinding = ActivityMyPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = MyPostAdapter(this, items)

        items.add(MyPostRecyclerItem("왕감자","대홍단 왕감자",10))
        items.add(MyPostRecyclerItem("왕감자","고구마 고구마 대홍단 왕감자",1))
        items.add(MyPostRecyclerItem("왕감자","대홍단 왕감자",0))
        items.add(MyPostRecyclerItem("왕감자","대홍단 왕감자",1))
        items.add(MyPostRecyclerItem("왕감자","감자 감자 대홍단 왕감자",0))
        items.add(MyPostRecyclerItem("왕감자","감자 대홍단 왕감자",1))
        items.add(MyPostRecyclerItem("왕감자","대홍단 왕감자",0))
        items.add(MyPostRecyclerItem("왕감자","고구마 감자 대홍단 왕감자",10))
        items.add(MyPostRecyclerItem("왕감자","대홍단 왕감자",20))



    }
}