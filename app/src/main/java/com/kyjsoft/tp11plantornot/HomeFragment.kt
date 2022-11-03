package com.kyjsoft.tp11plantornot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.kyjsoft.tp11plantornot.databinding.FragmentBoardBinding
import com.kyjsoft.tp11plantornot.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding
    var items: MutableList<HomeRecyclerItem> = mutableListOf()
    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerView.adapter = HomeAdapter(activity, items)

        loadData()
    }

    fun loadData(){
        items.add(HomeRecyclerItem("농사 주기","농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다."))
        items.add(HomeRecyclerItem("농사 주기","농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다."))
        items.add(HomeRecyclerItem("농사 주기","농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다."))
        items.add(HomeRecyclerItem("농사 주기","농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다."))
        items.add(HomeRecyclerItem("농사 주기","농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다."))
        items.add(HomeRecyclerItem("농사 주기","농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다."))

    }

}