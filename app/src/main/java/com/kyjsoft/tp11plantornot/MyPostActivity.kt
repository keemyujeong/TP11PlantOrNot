package com.kyjsoft.tp11plantornot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kyjsoft.tp11plantornot.databinding.ActivityMyPostBinding

class MyPostActivity : AppCompatActivity() {

    var items : MutableList<MyPostRecyclerItem> = mutableListOf()
    val binding : ActivityMyPostBinding by lazy{ ActivityMyPostBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.adapter = MyPostAdapter(this, items)
        binding.iv.setOnClickListener { onBackPressed() }


        // TODO MYSQL에서 해당 아이디의 제목,글,좋아요 갯수만 가져오면 됨.

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