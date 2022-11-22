package com.kyjsoft.tp11plantornot

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
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

        items.clear()
        binding.recyclerView.adapter?.notifyDataSetChanged()

        binding.btn.setOnClickListener {
            val imm: InputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)

            loadData()
        }

        binding.swipeRefresh.setOnRefreshListener {
            loadData()
            binding.swipeRefresh.isRefreshing = false
        }



    }

    fun loadData(){
        val baseUrl = "http://ncpms.rda.go.kr/npmsAPI/"
        val insectApiKey = "2022d5474582821a4f984e2b8988fecca95c"
        // TODO xml 파싱코드 쓰셈





    }
}

