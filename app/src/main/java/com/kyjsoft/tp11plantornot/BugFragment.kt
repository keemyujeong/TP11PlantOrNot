package com.kyjsoft.tp11plantornot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kyjsoft.tp11plantornot.databinding.FragmentBoardBinding
import com.kyjsoft.tp11plantornot.databinding.FragmentBugBinding

class BugFragment: Fragment() {

    lateinit var binding: FragmentBugBinding
    var items : MutableList<BugRecyclerItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBugBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = BugAdapter(requireContext(),items)

        items.add(BugRecyclerItem("감자","벌레",""))
        items.add(BugRecyclerItem("감자","벌레벌레벌레벌레",""))
        items.add(BugRecyclerItem("감자","벌레",""))
        items.add(BugRecyclerItem("감자","벌레벌레벌레벌레벌레",""))
        items.add(BugRecyclerItem("감자","벌레벌레",""))
        items.add(BugRecyclerItem("감자","벌레벌레",""))
        items.add(BugRecyclerItem("감자","벌레",""))
        items.add(BugRecyclerItem("감자","벌레벌레",""))
        items.add(BugRecyclerItem("감자","벌레",""))
        items.add(BugRecyclerItem("감자","벌레벌레",""))
        items.add(BugRecyclerItem("감자","벌레",""))
        items.add(BugRecyclerItem("감자","벌레벌레벌레벌레",""))
        items.add(BugRecyclerItem("감자","벌레벌레",""))
        items.add(BugRecyclerItem("감자","벌레",""))

    }
}