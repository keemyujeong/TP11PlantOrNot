package com.kyjsoft.tp11plantornot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kyjsoft.tp11plantornot.databinding.ActivityPickBinding

class PickActivity : AppCompatActivity() {

    var items:MutableList<PickRecyclerItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPickBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = PickAdapter(this, items)

        loadData()





    }

    fun loadData() {
        items.add(PickRecyclerItem("감자"))
        items.add(PickRecyclerItem("고구마"))
        items.add(PickRecyclerItem("마늘"))
        items.add(PickRecyclerItem("벼"))
        items.add(PickRecyclerItem("옥수수"))
        items.add(PickRecyclerItem("맛있겠다"))
        items.add(PickRecyclerItem("밤고구마"))
        items.add(PickRecyclerItem("먹어야지"))
        items.add(PickRecyclerItem("감자"))
        items.add(PickRecyclerItem("고구마"))
        items.add(PickRecyclerItem("마늘"))
        items.add(PickRecyclerItem("벼"))
        items.add(PickRecyclerItem("옥수수"))
        items.add(PickRecyclerItem("맛있겠다"))
        items.add(PickRecyclerItem("밤고구마"))
        items.add(PickRecyclerItem("먹어야지"))
        items.add(PickRecyclerItem("감자"))
        items.add(PickRecyclerItem("고구마"))
        items.add(PickRecyclerItem("마늘"))
        items.add(PickRecyclerItem("벼"))
        items.add(PickRecyclerItem("옥수수"))
        items.add(PickRecyclerItem("맛있겠다"))
        items.add(PickRecyclerItem("밤고구마"))
        items.add(PickRecyclerItem("먹어야지"))
        items.add(PickRecyclerItem("감자"))
        items.add(PickRecyclerItem("고구마"))
        items.add(PickRecyclerItem("마늘"))
        items.add(PickRecyclerItem("벼"))
        items.add(PickRecyclerItem("옥수수"))
        items.add(PickRecyclerItem("맛있겠다"))
        items.add(PickRecyclerItem("밤고구마"))
        items.add(PickRecyclerItem("먹어야지"))
        items.add(PickRecyclerItem("감자"))
        items.add(PickRecyclerItem("고구마"))
        items.add(PickRecyclerItem("마늘"))
        items.add(PickRecyclerItem("벼"))
        items.add(PickRecyclerItem("옥수수"))
        items.add(PickRecyclerItem("맛있겠다"))
        items.add(PickRecyclerItem("밤고구마"))
        items.add(PickRecyclerItem("먹어야지"))
        items.add(PickRecyclerItem("감자"))
        items.add(PickRecyclerItem("고구마"))
        items.add(PickRecyclerItem("마늘"))
        items.add(PickRecyclerItem("벼"))
        items.add(PickRecyclerItem("옥수수"))
        items.add(PickRecyclerItem("맛있겠다"))
        items.add(PickRecyclerItem("밤고구마"))
        items.add(PickRecyclerItem("먹어야지"))
    }
}