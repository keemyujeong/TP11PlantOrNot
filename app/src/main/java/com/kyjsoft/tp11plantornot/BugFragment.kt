package com.kyjsoft.tp11plantornot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kyjsoft.tp11plantornot.databinding.FragmentBoardBinding
import com.kyjsoft.tp11plantornot.databinding.FragmentBugBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

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

        binding.btn.setOnClickListener { loadData() }



    }

    fun loadData(){

        val bugApiKey = "2022d5474582821a4f984e2b8988fecca95c"
        val retrofit = RetrofitHelper.getInstance("http://ncpms.rda.go.kr/npmsAPI/service/")

        retrofit.create(RetrofitService::class.java).bugDataToString(
            bugApiKey,
            binding.etPlant.toString(),
            binding.etBug.toString()
        ).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("TAG", response.body().toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(requireContext(), "실패 : ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })


//        items.add(BugRecyclerItem("감자","벌레",""))
//        items.add(BugRecyclerItem("감자","벌레벌레벌레벌레",""))
//        items.add(BugRecyclerItem("감자","벌레",""))
//        items.add(BugRecyclerItem("감자","벌레벌레벌레벌레벌레",""))
//        items.add(BugRecyclerItem("감자","벌레벌레",""))
//        items.add(BugRecyclerItem("감자","벌레벌레",""))
//        items.add(BugRecyclerItem("감자","벌레",""))
//        items.add(BugRecyclerItem("감자","벌레벌레",""))
//        items.add(BugRecyclerItem("감자","벌레",""))
//        items.add(BugRecyclerItem("감자","벌레벌레",""))
//        items.add(BugRecyclerItem("감자","벌레",""))
//        items.add(BugRecyclerItem("감자","벌레벌레벌레벌레",""))
//        items.add(BugRecyclerItem("감자","벌레벌레",""))
//        items.add(BugRecyclerItem("감자","벌레",""))

    }
}