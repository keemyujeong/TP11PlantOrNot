package com.kyjsoft.tp11plantornot

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
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

        drawerToggle = ActionBarDrawerToggle(requireActivity(), binding.drawerId, binding.toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerToggle.syncState()
        binding.drawerId.addDrawerListener(drawerToggle)

        binding.nav.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_selectplant -> clickSelectPlant()
                R.id.menu_post -> clickMyPost()
                R.id.menu_location -> clickLocation()
            }
            binding.drawerId.closeDrawer(binding.nav)
            false

        } )

        binding.recyclerView.adapter = HomeAdapter(activity, items)

        loadData()
    }

    fun clickSelectPlant(){
        val intent : Intent = Intent(requireContext(), PickActivity::class.java)
        startActivity(intent)
    }

    fun clickMyPost(){
        val intent : Intent = Intent(requireContext(), MyPostActivity::class.java)
        startActivity(intent)
    }

    fun clickLocation(){
        val intent : Intent = Intent(requireContext(), MapActivity::class.java)
        startActivity(intent)
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